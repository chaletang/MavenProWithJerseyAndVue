$(function(){
	var rootURL = "http://localhost:8080/MyMavenWebTest/rest/test";	
	
	$('#btnUpdate').hide();	
	
	$.ajaxSetup({cache:false});
	
	getTest();
	
	$('#btnRefresh').on('click',function() {
		getTest();
	});
	
	$('#btnAdd').on('click',function() {
		addTest();
	});
	
	$(document).on('click', '.btnEdit', function(event) {
		event.preventDefault();
		var $ele = $(this).parents("tr");
		setValue($ele);
		$('#btnAdd').hide();
		$('#btnUpdate').show();	
	});
	
	$('#btnUpdate').on('click',function() {
		updateTest();
	});
	
	$('#btnRun').on('click',function() {
		var $checkedTR = $("#tablecontainer tbody").find("tr.selected");
		var id = $checkedTR.find("td").eq(1).text();
		runTest(id);
	});
	
	$('#btnRemove').on('click',function() {
		var $checkedTR = $("#tablecontainer tbody").find("tr.selected");
		var id = $checkedTR.find("td").eq(1).text();
		removeTest(id);
	});
	
	function setValue($ele){
		$("#testID").val($ele.children("td").eq(1).text()).attr("readonly","readonly");
		$("#testName").val($ele.children("td").eq(2).text());
		$("#testDes").val($ele.children("td").eq(3).text());
	}
	
	function setDefault(){
		$("#testID").val("").removeAttr("readonly");
		$("#testName").val("");
		$("#testDes").val("");
	}
	
	function formatHtml(data){
		var html = "";
		var checkTDBefore = "<td>" 
			+ "<div class='checkbox selectState'>";
		var checkTDAfter = "<span><i class='fa fa-check'></i></span>"
						+ "</label>"
						+ "</div>"
						+ "</td>";
		$.each(data, function(key, value){
			html += "<tr>";
			html += checkTDBefore;
			html += "<input type='checkbox' id='checkboxCustom-" + key + "' class='checkbox-custom'>";
			html += "<label for='checkboxCustom-" + key + "'>";
			html += checkTDAfter;
			html += "<td>" + value.testId + "</td>" + "<td>" + value.testName + "</td>" + "<td>" + value.testDes + "</td>";
			html += "<td><a class='btnEdit' href='#'>Edit</a></td></tr>"
		});
		$("#tablecontainer .table tbody").html("").append(html);
	}
	
	function getTest() {
		$.ajax({
			type: 'GET', 
			contentType: 'application/json; charSet=UTF-8',
			url: rootURL,
			success: function(data, textStatus, jqXHR){
				//alert('Test Loaded successfully');
				setDefault();
				formatHtml(data);
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('Test error: ' + textStatus);
			}
		});
	}

	function addTest() {
		$.ajax({
			type: 'POST',
			contentType: 'application/json;charset=UTF-8',
			url: rootURL,
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				//alert('Test created successfully');
				getTest();
				$('#btnAdd').show();
				$('#btnUpdate').hide();	
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('Test error: ' + textStatus);
			}
		});
	}
	
	function updateTest() {
		$.ajax({
			type: 'PUT',
			contentType: 'application/json;charset=UTF-8',
			url: rootURL,
			dataType: "json",
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				//alert('Test updated successfully');
				getTest();
				$('#btnAdd').show();
				$('#btnUpdate').hide();	
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('Test error: ' + textStatus);
			}
		});
	}
	
	function runTest(id) {
		$.ajax({
			type: 'GET',
			contentType: 'application/json;charset=UTF-8',
			url: rootURL + "/run/" + id,
			dataType: "json",
			data: id,
			success: function(data, textStatus, jqXHR){
				//alert('Test run successfully');
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('Test error: ' + textStatus);
			}
		});
	}
	
	function removeTest(id) {
		$.ajax({
			type: 'DELETE',
			contentType: 'application/json;charset=UTF-8',
			url: rootURL + "/remove/" + id,
			dataType: "json",
			data: id,
			success: function(data, textStatus, jqXHR){
				//alert('Test remove successfully');
				getTest();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('Test error: ' + textStatus);
			}
		});
	}
		

	function formToJSON() {
		return JSON.stringify({ 
			"testDes": $('#testDes').val(),
			"testId": $('#testID').val(), 
			"testName": $('#testName').val()
			});
	}
});
