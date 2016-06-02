$(document).ready(function(){
	//instancia a classe util;
	var util = new Util();
	
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
	
	
	
	$(".posted-by").on("click",  function() {
		  util.redirecionar('cadastro.html');
		});
	
	$('#form_entrar').validate({
			
		
		rules: {
				cpf: { required: true},
				dataNascimento: { required: true}
				
			},
			messages: {
				cpf: { required: 'Preencha o campo cpf' },
				dataNascimento: { required: 'Informe sua data de nascimento'}

			},
			submitHandler: function( form ){
				var dados = $( form ).serialize();

				$.ajax({
					type: "POST",
					url: "entrar",
					data: dados,
					beforeSend: function(){
						try {
							
							$("#envair").attr("value", "Consultando aguarde!");
							$('#envair').attr('disabled', 'disabled');
						} catch (e) {
							alert('erro ao carregar!')
						}
						
						
					},
					success: function( data )
					{
						$("#envair").attr("value", "CONSULTAR")
						$('#envair').removeAttr('disabled');
						if(util.converteStringParaBoolean(data)){
							
							util.redirecionar('menu.html');
							
						}else{
							alert("Seu cpf ou data de nascimento esta incorretos!");
						}
						
					}
				});

				
			}
		});
	});

