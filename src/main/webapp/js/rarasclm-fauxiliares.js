////////////////////////////////////////////////////////////////////////////
//
// DENO : Objeto CIP para el la validación del código de identificación de
//        paciente del SErvicio de Salud CAstilla-la Mancha,
//
// LICENCIA : MIT
//
// AUTOR : Ricardo Ortega Galiana (ricortegal@gmail.com)
//
// version 1.0.0
//
// https://github.com/ricortegal/jscip 
// git clone https://github.com/ricortegal/jscip.git
//
///////////////////////////////////////////////////////////////////////////


var CIP = function (cip, apellido1, apellido2, fecha, sexo) {
    
    var regCip15Control = /([A-Z]{2})([A-Z]{2})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{3})([0-9]{2})([0-9]{0,1}$)/;
    
    //atributos privados
    var pcip = "";
    var papellido1 = "";
    var papellido2 = "";
    var nombre = "";
    var psexo = "";
    var pfecha = "";
    var perror = -1;
    var pnumErrores = -1;
    
    //atributo público
    this.error = -1;
    this.numErrores = -1;

    
    if (typeof cip !== "undefined")
        pcip = cip;
    if (typeof apellido1 !== "undefined")
        papellido1 = apellido1;
    if (typeof apellido2 !== "undefined")
        papellido2 = apellido2;
    if (typeof sexo !== "undefined")
        psexo = sexo;
    if (typeof fecha !== "undefined")
        if (fecha instanceof Date)
            pfecha = fecha;
        else
            pfecha = new Date(fecha);
    
    
    var __apellido1_LL = null;
    var __apellido2_LL = null;
    var __anio_LL = null;
    var __mes_NN = null;
    var __dia_NN = null;
    var __prodencia_NNN = null;
    var __repeticion_NN = null;
    var __control_N = null;
    
    
    function okcip() {
        return regCip15Control.test(pcip);
    }
    
    
    function dosPrimerasLetrasCadena(cadena) {
        var regExpVocales = /[AEIOU]/g
        cadena = cadena.toUpperCase().replace("Ñ", "X");
        var consonantes = cadena.toUpperCase().replace(regExpVocales, "");
        if (consonantes.length > 1)
            return consonantes[0] + consonantes[1];
        else if (consonantes.length == 1) {
            return consonantes[0] + "X";
        } else {
            return "XX";
        }
    }
    
    function descompone() {
        if (okcip()) {
            gr = regCip15Control.exec(pcip);
            if (gr != null) {
                __apellido1_LL = gr[1];
                __apellido2_LL = gr[2];
                __anio_LL = gr[3];
                __mes_NN = parseInt(gr[4]);
                __dia_NN = parseInt(gr[5]);
                __prodencia_NNN = parseInt(gr[6]);
                __repeticion_NN = parseInt(gr[7]);
                __control_N = parseInt(gr[8]);
            }
        }
    }
    
    function analiza(self) {
    /*
        ///////////////////
        //ERRORES
        ///////////////////
       
        b0000000 0  sin error.
        b11111111 255  el cip no es válido.
        b00000010  2  el apellido1 no es válido.
        b00000100  4  el apellido2 no es valido.
        b00001000  8  el sexo no es válido.
        b00010000  16 el dia de nacimiento es erroneo.
        b00100000  32 el mes de nacimiento es erroneo.
        b01000000  64 el año de nacimiento es erroneo.
        b10000000 128 el cip no valida el dígito de control.
    */
        pnumErrores = 0;
        perror = 0;
        if (okcip()) {
            if (__apellido1_LL !== dosPrimerasLetrasCadena(papellido1)) {
                perror = perror | 2;
                pnumErrores++;
            }
            if (__apellido2_LL !== dosPrimerasLetrasCadena(papellido2)) {
                perror = perror | 4;
                pnumErrores++;
            }
            if (psexo == 9 || psexo == '') {
                perror = perror | 8;
                pnumErrores++;
            }
            if (__dia_NN == pfecha.getDate() && psexo == 6) {
                perror = perror | 8;
                pnumErrores++;
            }
            if ((__dia_NN == pfecha.getDate() + 40) && psexo == 1) {
                perror = perror | 8;
                pnumErrores++;
            }
            if (!( (__dia_NN == pfecha.getDate() + 40) || (__dia_NN == pfecha.getDate())) ) {
                perror = perror | 16;
                pnumErrores++;
            }
            if (__mes_NN !== pfecha.getMonth() + 1) {
                perror = perror | 32;
                pnumErrores++;
            }
            if (__anio_LL !== pfecha.toISOString().substring(2, 4)) {
                perror = perror | 64;
                pnumErrores++;
            }
        } else {
            perror = 255;
            pnumErrores = 8;
        }
        self.error = perror;
        self.numErrores = pnumErrores;
    }
    
    descompone();
    analiza(this);

};

CIP.prototype.getErrores = function () {
    
    //Completa la cadena binaria a 8bits
    function binario8char(cadena) {
        if (cadena.length >= 8) {
            return cadena;
        } else {
            return binario8char("0" + cadena);
        }
    }

    var ret = [];
    var _errores = {
        cip: { numError: 1, deno: "El cip no es correcto." },
        apellido1:  { numError: 2, deno: "El primer apellido no es correcto para el cip indicado." },
        apellido2:  { numError: 3, deno: "El segundo apellido no es correcto para el cip indicado." },
        fechaDia:   { numError: 4, deno: "El d\u00eda de la fecha de nacimiento no se corresponde con el cip indicado." },
        fechaMes:   { numError: 5, deno: "El mes de la fecha de nacimiento no se corresponde con el cip indicado." },
        fechaAnio:  { numError: 6, deno: "El a\u00f1o de la fecha de nacimiento no se corresponde con el cip indicado." },
        sexo:       { numError: 7, deno: "El sexo no es compatibe con los d\u00edgitos de fecha del cip." },
        cip_valida: { numError: 8, deno: "El d\u00edgito de control no es correcto." }
    };

    //Vamos comprobando errores
    if (this.error === 255) {
        ret.cip = _errores.cip;
        return ret;
    } else {
        //devuelve una cadena con la representación binaria
        //del número error (registro de errores)
        binerror = binario8char(this.error.toString(2));
        //El último valor del array del string que representa
        //el número binario es el menos siginificativo.
        if (binerror.charAt(7) == 1)
            ret.push(_errores.cip);
        if (binerror.charAt(6) == 1)
            ret.push(_errores.apellido1);
        if (binerror.charAt(5) == 1)
            ret.push(_errores.apellido2);
        if (binerror.charAt(4) == 1)
            ret.push(_errores.sexo);
        if (binerror.charAt(3) == 1)
            ret.push(_errores.fechaDia);
        if (binerror.charAt(2) == 1)
            ret.push(_errores.fechaMes);
        if (binerror.charAt(1) == 1)
            ret.push(_errores.fechaAnio);
        if (binerror.charAt(0) == 1)
            ret.push(_errores.cip_valida);
    }
    return ret;
}