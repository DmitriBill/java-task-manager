<!--showing password pointing at the eye with an arrow-->
$(".show-password").hover(
    function () {
        $("#password").attr("type", "text").focus();
    },
    function () {
        $("#password").attr("type", "password");
    }
);


$(window).scroll(function () {
    sessionStorage.scrollTop = $(this).scrollTop();
});

$(document).ready(function () {
    if (sessionStorage.scrollTop !== "undefined") {
        $(window).scrollTop(sessionStorage.scrollTop);
    }
});


$(document).ready(function () {
    $('#sortableTable').DataTable(
        {
            columnDefs: [
                {
                    ordering: false,
                    targets: [6, 7]
                }
            ],
            pageLength: 25
        });
    $('.dataTables_length').addClass('bs-select');
});


//Test accounts
$(document).ready(function () {
    $("#demo-manager-btn").click(function () {
        $("#email").val("supervisor@mail.com");
        $("#password").val("root123");
    });
});
$(document).ready(function () {
    $("#demo-maria-btn").click(function () {
        $("#email").val("maria@mail.com");
        $("#password").val("root123");
    });
});
$(document).ready(function () {
    $("#demo-mark-btn").click(function () {
        $("#email").val("john@mail.com");
        $("#password").val("root123");
    });
});
