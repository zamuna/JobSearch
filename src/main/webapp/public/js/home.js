/**
 * Created by Zamuna on 5/22/2017.
 */
function displayJObOffering() {
    var jo = document.getElementById('div_job_offering');
    console.log(jo);
    jo.style.display = 'block';
    var js = document.getElementById('div_job_souting');
    js.style.display = 'none';
}
function displayJObSought() {
    var jo = document.getElementById('div_job_offering');
    console.log(jo);
    jo.style.display = 'none';
    var js = document.getElementById('div_job_souting');
    js.style.display = 'block';

}