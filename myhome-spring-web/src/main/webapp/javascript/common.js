var footerTabs = {'home' : 0,
    '2' : 1,
    '25' : 2,
    '4' : 2,
    '17' : 3,
    '24' : 3


};
if($('#nav_list').length > 0){
    var fileNameIndex = location.pathname.lastIndexOf('/');
    var fileName = location.pathname.substr(fileNameIndex + 1);
    var footerIndex = footerTabs[fileName];
    var curLi = $('#nav_list li');

    curLi.eq(footerIndex).addClass('active').siblings().removeClass('active');

}

function switchTabs($tabsArr){
    for(var i= 0; i < $tabsArr.length; i++){
        var $tabs = $tabsArr[i];
        $tabs.on('click', function (e) {
            $(this).addClass('cur').siblings().removeClass('cur');
        });
    }
}
/**
 *cookie��ʽ��"key=val1;key2=val2;key3=val3"
 * �ж����ߣ�isonline="id=myid;name=myname"
 */
function parseCookie(){
    var cookies = document.cookie.split(';');
    var obj = null;
    for(var i = 0; i < cookies.length; i++){
        var flag = cookies[i].indexOf('isonline');
        if(flag >= 0){
            obj= {};
            // "id=myid;phone=myphone"
            /*
            var isOnLine = cookies[i].split('='); // a string
            var items =  isOnLine.split(';');
            var tmp = items[0].split('=');
            obj['id'] = tmp[1];
            tmp = items[1].split('=');
            obj['phone'] = tmp[1];
            */
            var isOnLine = cookies[i].split('='); // a string
            obj.phone = isOnLine[1].split(':')[1];
        }
    }
    return obj;
}
function parseUrl(){
    var url = location.href,
        s = url.indexOf('?');
    var obj = {};
    var queries = url.substring(s+1).split('&');
    for(var i = 0; i < queries.length; i++){
        var tmp = queries[i].split('=');
        var value=tmp[1];
        if(value && value.endsWith("#")) value=value.substr(0,value.length-1);
        obj[tmp[0]] = value;
    }
    return obj;
}

function getFormatDate(){
    var d = new Date();
    var t = d.getFullYear()+'-'+(d.getMonth() < 9 ? '0'+(d.getMonth() + 1) : d.getMonth() + 1 )+ '-'+
        (d.getDate() < 9 ? '0'+(d.getDate()+1) : (d.getDate()+1)) +' '+
        (d.getHours() < 9 ? '0'+(d.getHours()+1) : (d.getHours()+1))+':'+
        (d.getMinutes() < 9 ? '0'+(d.getMinutes()+1) : (d.getMinutes()+1));
    return t;
}