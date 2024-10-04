dropdownBrands = $("#brand");
dropdownCategories = $("#category");

$(document).ready(function() {

	$("#shortDescription").richText();
	$("#fullDescription").richText();

	dropdownBrands.change(function() {
		dropdownCategories.empty();
		getCategories();
	});

	getCategories();

});

function getCategories() {
	brandId = dropdownBrands.val();
	url = brandModuleURL + "/" + brandId + "/categories";

	$.get(url, function(responseJson) {
		$.each(responseJson, function(index, category) {
			$("<option>").val(category.id).text(category.name)
				.appendTo(dropdownCategories);
		});
	});
}

function checkUnique(form) {
	productId = $("#id").val();
	productName = $("#name").val();

	csrfValue = $("input[name='_csrf']").val();

	params = {
		id: productId,
		name: productName,
		_csrf: csrfValue
	};

	$.post(checkUniqueUrl, params, function(response) {
		if (response == "OK") {
			form.submit();
		} else if (response == "Dupliqué") {
			showWarningModal("Il existe un autre produit portant le même nom "
				+ productName);
		} else {
			showErrorModal("Réponse inconnue du serveur");
		}
	})
		.fail(
			function() {
				showErrorModal("Impossible de se connecter au serveur");
			});

	return false;
}