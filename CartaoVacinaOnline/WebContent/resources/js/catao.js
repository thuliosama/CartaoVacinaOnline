$(document).ready(function() {
	
	var todasVacinas='';
	var vacinasTomadas='';
	
	try {
		$.ajax({
			type : "Post",
			url : "cartao",
			async: false,
			success : function(data) {
				console.log(data);
				todasVacinas = data;
			}
		});
	} catch (e) {
		alert('erro = '+ e.toString());
	}
	
	
	var util = new Util();
	
	var urlCartao = "cartao?user="+util.getUrlVars()['user'];
	try {
		$.ajax({
			type : "GET",
			url : urlCartao,
			async: false,
			success : function(data) {
				console.log(data);
				vacinasTomadas = data;
			}
		});
	} catch (e) {
		alert('erro = '+ e.toString());
	}
		
	
	 controlador(todasVacinas,vacinasTomadas)
	
	
	
});


function controlador(todasVacinas,vacinasTomadas){
	var arrayVacinas;
	var jaTomou=false;
	var html = "<table border='1' class='table table-striped'><thead><tr>";
	html +="<th class='text-center'>Todas as Vacinas</th>";
	html +="<th class='text-center'>Vacinas Tomadas</th>";
	html +="</tr></thead><tbody> ";
	
	for(var i=0;i<todasVacinas.length;i++){
		html +="<tr>"
		html +="<td>"+todasVacinas[i].nomeVacina+"</td>"
		for(var j=0;j<vacinasTomadas.length;j++){
			if(todasVacinas[i].id == vacinasTomadas[j].id){
				html +="<td>"+vacinasTomadas[j].nomeVacina+"</td>"
				jaTomou =true;	
			}
		}
		if(jaTomou){
			
			jaTomou = false;
		}else{
			html +="<td><a href='#'>Tomar</a></td>"
		}
		
		html +="</tr>"
	}
	
	html +="</tbody></table>";
	$("#tabela").append(html)
}



 