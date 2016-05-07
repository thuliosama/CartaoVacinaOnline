

$(document).ready(function() {
var util = new Util();

	$("#CPF").blur(function(){
		 try {
			 $.ajax({
	               url: 'validaCPF',
	               type: 'POST',
	               data: 'cpf='+$('#CPF').val(),
	               success: function(response) {
	                  if(!util.converteStringParaBoolean(response)){
	                		$('#CPFInfo').css('display', 'block');
	            			$('#CPFInfo').html("Este CPF já esta cadastrado");
	            			$('#CPFInfo').css('color', 'red');
	                  }else{
	                	  $('#CPFInfo').html('');
	                  }
	               }            
	           });
		} catch (e) {
			alert('erro ao pedir a validaçao do cpf para a servlet! = '+e.toString());
		}
		
		
	});	
	
	


	
$('#form_cadastro').validate({
	  debug: true,
	success: function(label) {
		$.each( label, function( key, value ) {
			$('#'+value.control.id+'Info').html('');
		});
	},
	errorPlacement: function(error,element) {
		$.each( error, function( key, value ) {
			$('#'+value.control.id+'Info').css('display', 'block');
			$('#'+value.control.id+'Info').html(value.innerHTML);
			$('#'+value.control.id+'Info').css('color', 'red');
		});
	},
	
	rules: {
		nomeC:{required: true },
		dataNascimento: { required: true, dateBR:true},
		cpf: { required: true, verificaCPF: true },          
		recpf:{required: true, equalTo:"#CPF"}
	
		
	},
	messages: {
		nomeC:{ required: "O campo nome é obrigatório!" },
		dataNascimento: { required: 'A data de nascimento é obrigatório!', dateBR: 'Informe uma data de nascimento valida'},
		cpf: { required: 'O campo CPF é obrigatório!', verificaCPF: 'Digite um cpf valido!' },
		recpf:{required: "O campo repita seu cpf é obrigatório!", equalTo:"O valor informado não esta igual ao do campo cpf!"}
	},
	submitHandler: function( form ){
		var dados = $( form ).serialize();

		$.ajax({
			type: "POST",
			url: "cadastro",
			data: dados,
			success: function( data )
			{
				if(util.converteStringParaBoolean(data)){
					
					util.redirecionar('entrar.html?#msg=cadastroTrue');
				}
			}
		});

		return false;
	}
		});

		
	});

	
	


