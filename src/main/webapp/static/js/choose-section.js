$(document).ready(function () {
    $('#conference').change(function () {

        var conferenceId = $(this).val();
        var sectionDropbox = $('#section');
        sectionDropbox.empty();
        var conferencesString = $('#sections').val();
        var conferences = JSON.parse(conferencesString);

        for (i = 0; i < conferences.length; i++) {
            var confId = conferences[i].id;
            if (confId == conferenceId) {
                var sections = conferences[i].sections;
                for (j = 0; j < sections.length; j++) {
                    var section = sections[j];
                    if (section.status != "DEPRECATED") {
                        var newRow = "<option value=\"" + section.id + "\">" + section.name + "</option>";
                        sectionDropbox.append(newRow);
                    }
                }
            }
        }
    });
});