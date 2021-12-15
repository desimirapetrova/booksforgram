setInterval(function time(){
    var d = new Date();
    var hours = 17 - d.getHours();
    if(d.getHours()>17){
        hours=24-d.getHours()+17;
    }
    var min =60-d.getMinutes();
    if((min + '').length == 1){
        min = '0' + min;
    }
    var sec = 60 - d.getSeconds();
    if((sec + '').length == 1){
        sec = '0' + sec;
    }
    if(d.getHours()!=19&&d.getHours()!=18) {
        jQuery('#the-final-countdown p').html(hours + ':' + min + ':' + sec)
    }
}, 1000);