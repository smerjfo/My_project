
function calculate(){
    var n1,
        result,
        option1,
        option2;

    n1=document.getElementById('value').value;

    option1=document.getElementById('option1').value;
    var valueList2=option1.split('~');


    n1=parseInt(n1);


    option2=document.getElementById('option2').value;

    var valueList= option2.split("~");
    result=n1*parseFloat(valueList2[0])*parseFloat(valueList[1])/(parseFloat(valueList[0])*parseFloat(valueList2[1]));

    document.getElementById('value2').value=result.toFixed(4);

}



