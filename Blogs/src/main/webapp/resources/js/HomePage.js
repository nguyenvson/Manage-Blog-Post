let cmtLoadList = [];
let alreadyLoad = 0;
function loadComment(idPost){
    var nexUrl = "/blog/post/loadComment?idPost=" + idPost;
    for (let i = 0; i < cmtLoadList.length; i++ ){
        if(cmtLoadList[i] === idPost){
            alreadyLoad = 1;
            break;
        }
    };
    if (alreadyLoad == 0){
        $.ajax({ url: nexUrl, async: false, success: function (listCmt){
                $.each(listCmt, function( index, item ) {
                    let cmtRowLoad = "<div style='padding-left: 80px;color: #008d98'>" + item.author.name + ": " + item.content + "</div>";
                    $("#post-tags-cmts-"+idPost).append(cmtRowLoad);
                });
                let newInputCmtRow = "<span id=\"post-tags-cmts-list-"+ idPost +"\"></span>" +
                    "<form id=\"form-cmt-post-"+ idPost +"\" action=\"/blog/login\" method=\"get\" " +
                    "onsubmit=\"return submitCmt(" + idPost + ")\" class=\"form-cmt-post\">" +
                    "    <input type=\"text\" id=\"new-cmt-post-"+ idPost +"\" placeholder=\"Write comment...\" class=\"new-cmt\">" +
                    "    <input type=\"submit\" id=\"submit-cmt\" value=\"Comment\">" +
                    "</form>"
                $("#post-tags-cmts-" + idPost).append(newInputCmtRow);
                cmtLoadList.push(idPost);
            }
        })
    }
    alreadyLoad = 0;
};

function submitCmt(idPost){
    let cmt = $("#new-cmt-post-" + idPost).val();
    let checkCmtSuccess = 0;
    if (cmt != ""){
        var nexUrl = "/blog/new-cmt/" + idPost + "/" + cmt;
        $.ajax({ url: nexUrl, method: "get", async: false, success: function (){
            let cmtSuccessNotedRow = "<div style='color: gray; margin-left: 80px'>Your comment is approving...</div>";
            let newInputCmtRow = "<span id=\"post-tags-cmts-list-"+ idPost +"\"></span>" +
                "<form id=\"form-cmt-post-"+ idPost +"\" action=\"/blog/login\" method=\"get\" " +
                "onsubmit=\"return submitCmt(" + idPost + ")\" class=\"form-cmt-post\">" +
                "    <input type=\"text\" id=\"new-cmt-post-"+ idPost +"\" placeholder=\"Write comment...\" class=\"new-cmt\">" +
                "    <input type=\"submit\" id=\"submit-cmt\" value=\"Comment\">" +
                "</form>"
            $("#form-cmt-post-" + idPost).remove();
            $("#post-tags-cmts-list-" + idPost).append(cmtSuccessNotedRow);
            $("#post-tags-cmts-" + idPost).append(newInputCmtRow);
            checkCmtSuccess = 0;
        },
        error: function(){
            checkCmtSuccess = 1;
            alert("You have to login before comment.")
        }
        })
    }
    return checkCmtSuccess != 0;
};
let size = 2;
let page = 1;
function loadMore() { var nextUrl = "/blog/loadMorePost?page=" + page + "&size=" + size;
    $.ajax({ url: nextUrl, success: function (postList) {
            $.each(postList, function(index, item) {
                let tags = "";
                $.each(item.tags, function(tagIndex, tag) {
                    tags += "<a href=\"\">"+ (tag.name) +"</a>, ";
                });
                let newPostLoad = "<div class=\"post\">\n" +
                    "<div class=\"post-title\"><b>" + item.title + "</b></div>\n" +
                    "<div class=\"post-day-create\">Posted by " + item.user.name + " on " + item.dateCreate + "</div>\n" +
                    "<div class=\"post-content\">" + item.content + "</div>\n" +
                    "<div class=\"post-tags-cmts\" id=\"post-tags-cmts-" + item.id + "\">\n" +
                    "<div class=\"post-tag\">Tags: "+ tags +  "\n" +
                    "</div>\n" +
                    "<div class=\"post-cmt\"><a href=\"\">Permalink</a> | <span id=\"post-comment\" " +
                    "onclick=\"loadComment(" + item.id + ")\">Comments (" + item.comments.length + ")\n" +
                    "</span>| <span class=\"post-day-update\">Last updated on " + item.dateUpdate + "</span>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>";
                $("#post-content-left").append(newPostLoad);
            })
            page++;
        }
    });
};