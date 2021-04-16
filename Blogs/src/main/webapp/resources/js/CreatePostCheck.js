const RegCheck = /[(a-z)(A-Z)(0-9)].{2,}/g;
const RegTagCheck = /[(a-z)(A-Z)(0-9)].{1,}/g;
function createPostCheck(){
    var titleCheck = false;
    var contentCheck = false;
    var tagsCheck = false;
    var title = document.getElementById("Title");
    var content = document.getElementById("Content");
    var tags = document.getElementById("Tags");
    if (title.value.match(RegCheck)){
        titleCheck = true;
        document.querySelector('#errorTitle').innerText = '';
    }
    else {
        document.querySelector('#errorTitle').innerText = 'Title must contain at least 3 characters';
    }

    if (content.value.match(RegCheck)){
        contentCheck = true;
        document.querySelector('#errorContent').innerText = '';
    }
    else {
        document.querySelector('#errorContent').innerText = 'Content must contain at least 3 characters';
    }

    if (tags.value.match(RegTagCheck)){
        tagsCheck = true;
        document.querySelector('#errorTags').innerText = '';
    }
    else {
        document.querySelector('#errorTags').innerText = 'Tags must contain at least 2 characters';
    }

    return titleCheck && tagsCheck && contentCheck;
}