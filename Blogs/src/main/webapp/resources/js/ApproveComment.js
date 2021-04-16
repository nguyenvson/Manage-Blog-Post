function submitApprove(idCmt){
    numberApproveCmtChange(idCmt);
    $("#status-updated-" + idCmt).remove();
    var nexUrl = "/blog/updateStatusCmt/" + idCmt + "/" + $("#select-status-cmt-" + idCmt).val();
    $.ajax({ url: nexUrl, method: "put", async: false, success: function (){
            document.body.style.cursor = "wait"
            setTimeout(function(){
                document.body.style.cursor = "default";
                let rowUpdated = "<span id=\"status-updated-"+ idCmt + "\" style='font-size: 80%; color: gray'>Updated.</span>";
                $("#status-cmt-" + idCmt).append(rowUpdated);
            }, 1000);
        },
        error: function(){
            alert("Action Fail !!!")
        }
    })
};
function deleteCmt(idCmt){
    numberDeleteCmtChange(idCmt);
    var nexUrl = "/blog/deleteCmt/" + idCmt;
    $.ajax({ url: nexUrl, method: "delete", success: function (selu){
            let numberCmt = parseInt($("#number-cmt").text());
            if (numberCmt >= 0){
                $("#number-cmt").remove();
                var newNumberCmt = "<span id=\"number-cmt\">"+ --numberCmt + "</span>";
                $("#contain-number-cmt").append(newNumberCmt);
                $("#cmt-" + idCmt).remove();
            }
        },
        fail: function(){
            console.log("fail");
        }
    })
}
function numberApproveCmtChange(idCmt){
    let cmtStatus = $("#select-status-cmt-" + idCmt).val();
    let numberApproveCmt = parseInt($("#number-approve-cmt").text());
    if (cmtStatus == "Approve"){
        numberApproveCmt--;
    }
    if (cmtStatus == "Disapprove"){
        numberApproveCmt++;
    }
    if (numberApproveCmt >= 0){
        var newNumberCmt = "<span id=\"number-approve-cmt\">"+ numberApproveCmt + "</span>";
        $("#number-approve-cmt").remove();
        $("#contain-number-approve-cmt").append(newNumberCmt);
    };
}
function numberDeleteCmtChange(idCmt){
    let cmtStatus = $("#select-status-cmt-" + idCmt).val();
    let numberApproveCmt = parseInt($("#number-approve-cmt").text());
    if (cmtStatus == "Approve"){
        numberApproveCmt++;
    }
    if (cmtStatus == "Disapprove"){
        numberApproveCmt--;
    }
    if (numberApproveCmt >= 0){
        var newNumberCmt = "<span id=\"number-approve-cmt\">"+ numberApproveCmt + "</span>";
        $("#number-approve-cmt").remove();
        $("#contain-number-approve-cmt").append(newNumberCmt);
    };
}