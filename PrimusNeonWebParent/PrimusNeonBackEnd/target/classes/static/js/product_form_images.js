var extraImagesCount = 0;


$(document).ready(function() {

	$("input[name='extraImage']").each(function(index) {
		extraImagesCount++;

		$(this).change(function() {
			if (!checkFileSize(this)) {
				return;
			}
			showExtraImageThumbnail(this, index);
		});
	});

});

function showExtraImageThumbnail(fileInput, index) {
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		$("#extraThumbnail" + index).attr("src", e.target.result);
	};

	reader.readAsDataURL(file);

	if (index >= extraImagesCount - 1) {
		addNextExtraImageSection(index + 1);
	}
}

function addNextExtraImageSection(index) {
	htmlExtraImage = `
		<div class="col border m-3 p-2" id="divExtraImage${index}">
			<div class="p-2" id="extraImageHeader${index}"><label>Image Supplémentaire #${index + 1}:</label></div>
			<div>
				<img id="extraThumbnail${index}" alt="Aperçu de l'image supplémentaire #${index + 1}" class="img-fluid p-2" src="${defaultImageThumbnailSrc}" />
			</div>
			<div class="m-2">
				<input type="file" name="extraImage" 
					onchange="showExtraImageThumbnail(this, ${index})"
					accept="image/png, image/jpeg, image/webp" />
			</div>
		</div>
	`;

	htmlLinkRemove = `
		<a class="btn fas fa-times-circle fa-2x icon-dark float-end" 
		href="javascript:removeExtraImage(${index - 1})"
		title="Supprimer cette image"></a>
	`;

	$("#divProductImages").append(htmlExtraImage);

	$("#extraImageHeader" + (index - 1)).append(htmlLinkRemove);

	extraImagesCount++;
}

function removeExtraImage(index) {
	$("#divExtraImage" + index).remove();
}