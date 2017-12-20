$(document).ready(function(){
    var otpCode = 0;
    $("#get-code").on("click",function(){
        $("#myModal").show();
        otpCode = otpCodeGenerator(10000, 99999);
        setTimeout(function(){
            $("#myModal").hide();
            $("#otp-code").html('Please enter the OTP code as: <span class="label label-success control-label">' + otpCode + '</span> to confirm your transaction.');
            $("#get-code").hide();
            $("#otp-form").show();
        },5000);
    });
    
    var checkoutForm = $("#check-out-form").find("form");
    var counter = 0;
    if(checkoutForm){
        checkoutForm.submit(function(e){
           var otpEntered = $("#otp-code-input").val();
           while(counter < 3){
               if(otpEntered != otpCode){
                   counter++;
                   e.preventDefault();
                   alert('OTP code is incorrect ' + counter + " time(s). After 3 attempts, the new code will be issued !!!");
                   break;
               }
               else{
                   counter = 0;
                   break;
               }
           }
           if(counter == 3){
               counter = 0;
               $("#myModal").show();
               setTimeout(function(){
                   $("#myModal").hide();
                   otpCode = otpCodeGenerator(10000, 99999);
                   $("#otp-code").html('Please enter the OTP code as: <span class="label label-success control-label">' + otpCode + '</span> to confirm your transaction.');
               },5000)
           }
        });
    }
    //for randomly generate opt code
    var otpCodeGenerator = function(max, min){
        return  Math.floor(Math.random() * (max - min + 1)) + min;
    }
    
    $('.rate-area input[type=radio][name="rating"]').click(function(){
        if($(this).is(':checked')){
            var ratingValue = $(this).val();
            $('#rating-box > input[type=text]').val(ratingValue);
        }
    })
});

