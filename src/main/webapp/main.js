function getData()
{
	$("#data3").val("Duck");
	$.get( "ping", function( data ) {
	  $( ".result" ).html( data );
	  document.getElementById("data4").value=data;
	});
}