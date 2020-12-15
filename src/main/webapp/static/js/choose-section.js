$(document).ready(function () {
	$('#conference').change(function () {

		var conferenceId = $(this).val();
		var sectionDropbox = $('#section');
		sectionDropbox.empty();
		var sectionString = $('#sections').val();
		var sectionMap = JSON.parse(sectionString);

		for (i = 0; i < sectionMap.length; i++) {
			var sections = sectionMap[i].sections;
			for(j=0; j< sections.length; j++ ){
				var section = sections[j];
				var sectConfId = section.conferenceId;
				if (sectConfId == conferenceId){
					var newRow = "<option value=\"" + section.id + "\">" + section.name + "</option>";
					sectionDropbox.append(newRow);
				}
			}
		}
	});
});
