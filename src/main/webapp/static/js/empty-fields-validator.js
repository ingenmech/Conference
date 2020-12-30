//TODO extra realisation, in project used HTML5 attributes (KISS principle)

 $(document).ready(function () {
     $('input[type="submit"]').attr('disabled', true);

     $('input[type="text"],textarea,input[type="date"],input[type="time"]').on('change', function () {
         var textarea_value = $("#name-conference").val();
         var dateArea = $("#date").val();
         var timeArea = $("#time").val();
         var textValue = [];
         $('input').each(function() {
             var id = $(this).attr('id');
             if (id == 'section'){
             var temp = $(this).val();
             textValue.push(temp);
             }
         });
         var emptySection;
        for (var i = 0; i < textValue.length; i++) {
            emptySection = textValue[i];
            if (emptySection === ''){
                break;
            }
        }

         if (textarea_value != '' && emptySection != '' && dateArea != '' && timeArea != '') {
             $('input[type="submit"]').attr('disabled', false);
         } else {
             $('input[type="submit"]').attr('disabled', true);
         }
     });
 });
<script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath}/static/js/empty-fields-validator.js"></script>

<div class="message">
		     <c:if test="${message eq 'conferenceCreated' }">
         		 <h3>${message}</h3>
             </c:if>
		 </div>