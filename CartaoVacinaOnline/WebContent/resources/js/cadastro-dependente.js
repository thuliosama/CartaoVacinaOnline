$(document).ready(function(){
	//instancia a classe util;
	var util = new Util();
	

	$('#form_cadastra_dependente').validate({
			
		
		rules: {
				nomeC: { required: true},
				dataNascimento: { required: true}
				
			},
			messages: {
				nomeC: { required: 'Preencha o campo nome' },
				dataNascimento: { required: 'Informe sua data de nascimento'}

			},
			submitHandler: function( form ){
				var dados = $( form ).serialize();

				$.ajax({
					type: "POST",
					url: "cadastroDependente",
					data: dados,
					beforeSend: function(){
						try {
							
							$("#envair").attr("value", "Cadastrando aguarde!");
							$('#envair').attr('disabled', 'disabled');
						} catch (e) {
							alert('erro ao carregar!')
						}
					},
					success: function( data )
					{
						$("#envair").attr("value", "CADASTRAR")
						$('#envair').removeAttr('disabled');
						if(util.converteStringParaBoolean(data)){
							
							util.redirecionar('menu.html#msg=cadastroTrue');
							
						}else{
							alert("Seu cpf ou data de nascimento estão incorretos!");
						}
						
					}
				});

				
			}
		});
	});
