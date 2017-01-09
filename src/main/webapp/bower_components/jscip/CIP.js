////////////////////////////////////////////////////////////////////////////
//
// DENO : Objeto CIP para el la validación del código de identificación de
//        paciente del Servicio de Salud Castilla-la Mancha,
//
// LICENCIA : MIT
//
// AUTOR : Ricardo Ortega Galiana (ricortegal@gmail.com)
//
// version 1.0.4
//
// https://github.com/ricortegal/jscip 
// git clone https://github.com/ricortegal/jscip.git
//
///////////////////////////////////////////////////////////////////////////


var CIP = function(cip, apellido1, apellido2, fecha, sexo) {

    var regCip15Control = /([A-Z]{2})([A-Z]{2})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{3})([0-9]{2})([0-9]{0,1}$)/;

    if (cip instanceof Object) {
        _cip = cip.cip;
        _apellido1 = cip.apellido1;
        _apellido2 = cip.apellido2;
        _fecha = cip.fecha;
        _sexo = cip.sexo;
    } else {
        _cip = cip;
        _apellido1 = apellido1;
        _apellido2 = apellido2;
        _fecha = fecha;
        _sexo = sexo;
    }

    var __apellido1 = "";
    var __apellido2 = "";
    var __nombre = "";
    var __sexo = "";
    var __fecha = "";
    var __error = -1;
    var __numErrores = -1;
    var __apellido1_LL = null;
    var __apellido2_LL = null;
    var __anio_LL = null;
    var __mes_NN = null;
    var __dia_NN = null;
    var __prodencia_NNN = null;
    var __repeticion_NN = null;
    var __control_N = null;

    //inicializacion de las variables
    if (typeof _cip !== "undefined")
        __cip = _cip;
    if (typeof _apellido1 !== "undefined")
        __apellido1 = _apellido1;
    if (typeof _apellido2 !== "undefined")
        __apellido2 = _apellido2;
    if (typeof _sexo !== "undefined")
        __sexo = _sexo;
    else
        __sexo = 9;
    if (typeof _fecha !== "undefined") {
        if (_fecha instanceof Date) {
            __fecha = _fecha;
        } else if (_fecha != null && _fecha !== "") {
            var timestamp = Date.parse(_fecha)
            if (isNaN(timestamp) == false) {
                __fecha = new Date(timestamp);
            } else {
                __fecha = null;
            }
        } else {
            __fecha = null;
        }
    }

    //atributos públicos
    this.cip = __cip;
    this.apellido1 = __apellido1;
    this.apellido2 = __apellido2;
    this.fechaNacimiento = __fecha;
    this.sexo = __sexo;
    this.error = -1;
    this.numErrores = -1;


    function okcip() {
        return regCip15Control.test(__cip);
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
            gr = regCip15Control.exec(__cip);
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
            b00001000  8  el sexo no es válido para el cip dado.
            b00010000  16 el dia de nacimiento es erroneo.
            b00100000  32 el mes de nacimiento es erroneo.
            b01000000  64 el año de nacimiento es erroneo.
            b10000000 128 el cip no valida el dígito de control.
        */
        pnumErrores = 0;
        perror = 0;
        if (okcip()) {
            if (__apellido1_LL !== dosPrimerasLetrasCadena(__apellido1)) {
                perror = perror | 2;
            }
            if (__apellido2_LL !== dosPrimerasLetrasCadena(__apellido2)) {
                perror = perror | 4;
            }
            if (!(__sexo == 6 || __sexo == 1)) {
                perror = perror | 8;
            }
            if (__fecha != null) {
                if (!(__dia_NN === __fecha.getDate() || __dia_NN === __fecha.getDate() + 40)) {
                    perror = perror | 16
                }
                if (__dia_NN === __fecha.getDate() && __sexo === 6) {
                    perror = perror | 8;
                }
                if (__dia_NN === __fecha.getDate() + 40 && __sexo == 1) {
                    perror = perror | 8;
                }
                if (__dia_NN > 40 && __sexo == 1) {
                    perror = perror | 8;
                }
                if (__dia_NN < 40 && __sexo == 6) {
                    perror = perror | 8;
                }
                if (__mes_NN !== __fecha.getMonth() + 1) {
                    perror = perror | 32;
                }
                if (__anio_LL !== __fecha.toISOString().substring(2, 4)) {
                    perror = perror | 64;
                }
            } else {
                perror = perror | 8;
                perror = perror | 16;
                perror = perror | 32;
                perror = perror | 64;
            }
            var errorBin = perror.toString(2)
            for (i = 0; i < errorBin.length; i++) {
                if (errorBin[i] === '1')
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


CIP.prototype.getErrores = function() {

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
        apellido1: { numError: 2, deno: "El primer apellido no es correcto para el cip indicado." },
        apellido2: { numError: 3, deno: "El segundo apellido no es correcto para el cip indicado." },
        fechaDia: { numError: 4, deno: "El día de la fecha de nacimiento no se corresponde con el cip indicado." },
        fechaMes: { numError: 5, deno: "El mes de la fecha de nacimiento no se corresponde con el cip indicado." },
        fechaAnio: { numError: 6, deno: "El año de la fecha de nacimiento no se corresponde con el cip indicado." },
        sexo: { numError: 7, deno: "El sexo no es compatibe con los dígitos de fecha del cip." },
        cip_valida: { numError: 8, deno: "El dígito de control no es correcto." }
    };

    //Vamos comprobado errores
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


//Exportamos el objeto CIP al paquete npm
if (typeof module !== 'undefined')
    module.exports.CIP = CIP;