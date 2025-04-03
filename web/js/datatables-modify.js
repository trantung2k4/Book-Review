window.addEventListener('DOMContentLoaded', event => {

    if (datatableProduct) {
        new simpleDatatables.DataTable("#datatableProduct", {
            labels: {
                placeholder: "Tìm kiếm ...",
                perPage: "{select} Sản phẩm hiển thị",
                noRows: "Không có sản phẩm",
                info: "Hiển thị {start} tới {end} của {rows} sản phẩm",
            }
        });
    }

    if (datatableAccount) {
        new simpleDatatables.DataTable("#datatableAccount", {
            labels: {
                placeholder: "Tìm kiếm ...",
                perPage: "{select} Tài khoản hiển thị",
                noRows: "Không có tài khoản",
                info: "Hiển thị {start} tới {end} của {rows} tài khoản",
            }
        });
    }
});


