$(document).ready(function() {
	
	try {
		$("#logOff").on("click",function() {
		
				$.ajax({
					type : "POST",
					url : "entrar?logOff=sair",
					
					success : function(data) {
						var util = new Util();
						util.redirecionar("entrar.html")
					}
				});
				var util = new Util();
				util.redirecionar("entrar.html")
			});
		} catch (e) {
			alert('erro! logof = ' +window.location.href + ' erro == '+e.toString())
		}
	
		
	//instancia a classe util;
	var util = new Util();
	
	
	
	$("#cadastrarNovoDependente").on( "click", function() {
		util.redirecionar('cadastro_dependente.html'); 
	});
	
	//pega o hash para avisar que o cadastro foi realizado com sucesso!
	try{
		var cadastroRealizadoComSucesso ='msg=cadastroTrue'; 
		
		if(!cadastroRealizadoComSucesso.localeCompare(window.location.href.split("#")[1])){
			alert('Cadastro realizado com sucesso!');
			 window.location.hash = '';
		}
	}catch (e) {
		alert('Erro na classe entrar.js = '+ e.toString());
	}
	

	// busca os valores da tela menu
	try {
	
		$.ajax({
			type : "POST",
			dataType : 'json',
			url : "menu",
			contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
			success : function(data) {
				console.log(data);
				controlador(data);
				
			}
		});
	} catch (e) {
		alert('erro menu.js = '+e.toString())
	}
	

});

function redirecionaCartao(id){
	var util = new Util();
	alert('clicou');
//	util.redirecionar("cartao.html?user="+id)
}

function controlador(data){
	$('#nomeUsuario').html(msgBemVindoUsuario(data.nome));
	linkUsuario(data);
	$(".btnUser").on("click",function() {
		var idUsuario = $(this).attr('href');
		var util = new Util();
		util.redirecionar('cartao.html?user='+idUsuario);
		
		
	});
}

function msgBemVindoUsuario(nomeUsuario){
	if(nomeUsuario == null){
		return "";
	}else{
		return nomeUsuario;
	}
}
function linkUsuario(data){
	var html = '';
	if(data != null){
		html+='<a href="'+data.id+'"  class="element-animation1 btnUser btn btn-lg btn-primary btn-block">Visualizar cartao de vacina '+data.nome+'</a>';
		for(var i=0;i<data.listaDependentes.length;i++){
			html+='<a href="'+data.listaDependentes[i].id+'"  class="element-animation1 btnUser btn btn-lg btn-primary btn-block">Visualizar cartao de vacina '+data.listaDependentes[i].nome+'</a>';
		}
		
		$('.menuButton').prepend(html);
		
	
	}
}