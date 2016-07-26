/**
 * 
 */

function extraeCIE9mc(cadena) {
	var regCie9mc = /(\b[\d]{3}\.[\d]{1,2}\b|\b[\d]{3}\b)/g;
	var coincidencias = cadena.match(regCie9mc);
	if(coincidencias.length>0 && coincidencias[0]!=null)
		return coincidencias[0];
	else
		return null;
}


function extraeCIE10(cadena) {
	var regCie10 =/(^[A-Z]\d{2}.\d{1}$)|([A-Z]\d{2}$)/g
	var coincidencias = cadena.match(regCie10);
	if(coincidencias.length>0 && coincidencias[0]!=null)
		return coincidencias[0];
	else
		return null;
}