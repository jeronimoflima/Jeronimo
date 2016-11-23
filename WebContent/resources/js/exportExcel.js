 $('#btnExport').click(function() {
	  var doc = new jsPDF('landscape', 'pt', 'a4');
	  $('#principal').find('table tr').each(function(){
		  $(this).find('th:last,td:last').hide();
	  });
	  $("#busca").hide();
	  doc.addHTML($('#principal'), function() {
	    doc.save("teste.pdf");
	    $('#principal').find('table tr').each(function(){
			  $(this).find('th:last,td:last').show();
		  });
	    $("#busca").show();
	  });
	});