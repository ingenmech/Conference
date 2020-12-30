$(document).ready(function () {
	$('#conference').change(function () {

		var conferenceId = $(this).val();
		var sectionDropbox = $('#section');
		sectionDropbox.empty();
		var conferencesString = $('#sections').val();
		var conferences = JSON.parse(conferencesString);

		for(i=0; i < conferences.length; i++){
			var confId = conferences[i].id;
			if(confId == conferenceId){
				var sections = conferences[i].sections;
				for(j=0; j < sections.length; j++ ){
					var section = sections[j];
					var newRow = "<option value=\"" + section.id + "\">" + section.name + "</option>";
					sectionDropbox.append(newRow);
				}
			}
		}
	});
});

// $(document).ready(function () {
// 	$('#conference').change(function () {

// 		var conferenceId = $(this).val();
// 		var sectionDropbox = $('#section');
// 		sectionDropbox.empty();
// 		var sectionString = $('#sections').val();
// 		var sections = JSON.parse(sectionString);

// 			for(j=0; j < sections.length; j++ ){
// 				var section = sections[j];
// 				var sectConfId = section.conferenceId;
// 				if (sectConfId == conferenceId){
// 					var newRow = "<option value=\"" + section.id + "\">" + section.name + "</option>";
// 					sectionDropbox.append(newRow);
// 				}
// 			}
		
// 	});
// });

//<!-- <c:set var="listJson">[<c:forEach var="conferences" items="${conferenceList}" varStatus="status"><c:forEach items="${conferences.sections}" var="sect" varStatus="statusSect">{"conferenceId":"${sect.conferenceId}","name":"${sect.name}","id":"${sect.id}"}<c:if test="${!statusSect.last}">,</c:if></c:forEach><c:if test="${!status.last}">,</c:if></c:forEach>]</c:set> -->