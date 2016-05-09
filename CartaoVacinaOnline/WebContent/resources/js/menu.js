$(document).ready(function() {
	//instancia a classe util;
	var util = new Util();
	
	$("#cadastrarNovoDependente").on( "click", function() {
		util.redirecionar('cadastro_dependente.html'); 
	});

	// busca os valores da tela menu
	alert('chamando metodo');
	try {
	
		$.ajax({
			type : "POST",
			dataType : 'json',
			url : "menu",
			contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
			success : function(data) {
				controlador(data);
				
				
			}
		});
	} catch (e) {
		alert('erro = '+e.toString())
	}
	

});

function controlador(data){
	$('#nomeUsuario').html(msgBemVindoUsuario(data.nome));
//	$('.menuButton').prepend(linkUsuario(data.nome));
	linkUsuario(data.nome);
}

function msgBemVindoUsuario(nomeUsuario){
	if(nomeUsuario == null){
		return "";
	}else{
		return nomeUsuario;
	}
}
function linkUsuario(usuario){
	if(usuario != null){
		$('.menuButton').prepend('<a href="#"  class=\'element-animation1 btn btn-lg btn-primary btn-block\'>Visualizar cartao de vacina '+usuario+'</a>');
	
	}
}