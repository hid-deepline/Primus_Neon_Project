function clearFilter() {
	window.location = moduleURL;
}

function showDeleteConfirmModal(link, entityName) {
	entityId = link.attr("entityId");

	$("#yesButton").attr("href", link.attr("href"));
	$("#confirmText").text("Êtes-vous sûr de vouloir supprimer cette "
		+ entityName + " ID " + entityId + "?");
	$("#confirmModal").modal("show");
}