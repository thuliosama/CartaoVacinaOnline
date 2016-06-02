try {
	


jQuery.validator.addMethod("verificaCPF", function(value, element) {
	    value = value.replace('.','');
	    value = value.replace('.','');
	    cpf = value.replace('-','');
	    while(cpf.length < 11) cpf = "0"+ cpf;
	    var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;
	    var a = [];
	    var b = new Number;
	    var c = 11;
	    for (i=0; i<11; i++){
	        a[i] = cpf.charAt(i);
	        if (i < 9) b += (a[i] * --c);
	    }
	    if ((x = b % 11) < 2) { a[9] = 0 } else { a[9] = 11-x }
	    b = 0;
	    c = 11;
	    for (y=0; y<10; y++) b += (a[y] * c--);
	    if ((x = b % 11) < 2) { a[10] = 0; } else { a[10] = 11-x; }
	    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) return false;
	    return true;
	}, "Informe um CPF válido."); // Mensagem padrão 
} catch (e) {
	
}
try {
	

jQuery.validator.addMethod("dateBR", function(value, element) {            
    //contando chars 
   if(value.length!=10) return false;
   // verificando data
   var data        = value;
   var dia         = data.substr(0,2);
   var barra1      = data.substr(2,1);
   var mes         = data.substr(3,2);         
   var barra2      = data.substr(5,1);
   var ano         = data.substr(6,4);         
   if(data.length!=10||barra1!="/"||barra2!="/"||isNaN(dia)||isNaN(mes)||isNaN(ano)||dia>31||mes>12)return false; 
   if((mes==4||mes==6||mes==9||mes==11)&&dia==31)return false;
   if(mes==2 && (dia>29||(dia==29&&ano%4!=0)))return false;
   if(ano < 1900)return false;
   return true;        
}, "Informe uma data válida");  // Mensagem padrão 
} catch (e) {
	// TODO: handle exception
}
var Util = function(){
		console.log('Instanciando classe util');
	}; 
	Util.prototype.converteStringParaBoolean = function(texto) {
		switch (texto.toLowerCase().trim()) {
		case "true":
		case "yes":
		case "1":
			console.log("retorna true");
			return true;

		case "false":
		case "no":
		case "0":
		case null:
			console.log("retorna false");
			return false;
		default:
			return Boolean(texto);
		}
	};

	Util.prototype.redirecionar = function(url) {
		location.href = url;	
	};
	Util.prototype.pathname = function() {
		return window.location.pathname;	
	};
	Util.prototype.url = function() {
		return window.location.href;	
	};
	
	Util.prototype.getHASH = function() {
		try {
			var hash = window.location.hash.slice(1);
		    var pares = hash.split('&');
		    var chaves = pares.map(function (par) {
		        var chave_valor = par.split('=');
		        return {
		            chave: chave_valor[0],
		            valor: chave_valor[1]
		        };
		    });
		    return chaves;
		} catch (e) {
			alert('erro no metodo getHash = '+e.toString());
		}
		
		
	};
	
	//modo de usar getUrlVars()["atributo"]
	Util.prototype.getUrlVars = function() {
		try {
			 var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			        hash = hashes[i].split('=');
			        vars.push(hash[0]);
			        vars[hash[0]] = hash[1];
			    }
			    return vars;
		} catch (e) {
			alert('erro no metodo getHash = '+e.toString());
		}
		
		
	};


$(document).ready(function() {
try{
	$('.cpf').mask('000.000.000-00', {
		reverse : true
	});
	$('.dataNasci').mask('00/00/0000');
	
}catch (e) {

}	
	

});


