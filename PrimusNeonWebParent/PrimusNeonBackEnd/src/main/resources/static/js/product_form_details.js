$(document).ready(function() {
	$("a[name = 'linkRemoveDetail']").each(function(index) {
		$(this).click(function() {
			removeDetailSectionByIndex(index);
		});
	});
});

function addNextDetailSection() {
	allDivDetails = $("[id^=divDetail]");
	divDetailsCount = allDivDetails.length;

	htmDetailSection = `
		<div class="row mb-2" id="divDetail${divDetailsCount}">
		<input type="hidden" name="detailIDs" value="0" />
			<div class="col-sm-3">
				<div class="input-group mb-2">
					<div class="input-group-text">Nom :</div>
					<input type="text" class="form-control w-25" name="detailNames" maxlength="255"/>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="input-group mb-2">
					<div class="input-group-text">Valeur :</div>
					<input type="text" class="form-control w-25" name="detailValues" maxlength="255" />
				</div>
			</div>
		</div>
	`;

	$('#divProductDetails').append(htmDetailSection);

	previousDivDetailSection = allDivDetails.last();
	previousDivDetailID = previousDivDetailSection.attr("id");

	htmlLinkRemove = `
		<div class="col-sm-2">
			<a class="btn fas fa-times-circle fa-2x icon-dark"
			    href="javascript:removeDetailSectionById('${previousDivDetailID}')"
				name="linkRemoveDetail" title="Supprimer ce détail"></a>
		</div>
		`;

	previousDivDetailSection.append(htmlLinkRemove);

	$("input[name='detailNames']").last().focus();
}

function removeDetailSectionById(id) {
	$("#" + id).remove();
}

function removeDetailSectionByIndex(index) {
	$("#divDetail" + index).remove();
}