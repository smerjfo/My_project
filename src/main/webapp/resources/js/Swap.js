function swap ()
{
        var tmp=document.getElementById('charCode1').value;
        document.getElementById('charCode1').value=document.getElementById('charCode2').value;
        document.getElementById('charCode2').value=tmp;
        var selectedItems=document.querySelectorAll('.select_current');
        let text1=selectedItems.item(0).innerText
        selectedItems.item(0).innerText=selectedItems.item(1).innerText
    selectedItems.item(1).innerText=text1;
}