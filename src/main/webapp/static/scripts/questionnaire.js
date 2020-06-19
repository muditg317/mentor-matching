function checkGender(val){
  var element=document.getElementById('gender');
  if(val=='other') {
    element.style.display='block';
  } else {
    element.style.display='none';
  }
}
