/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

let brand =
        {
            init: function () {
                brand.deleteBrand();
            },
            deleteBrand: function () {
                $('.brand__a__deleteBrand').each(function () {
                    $(this).on('click', function () {
                        let id = $(this).data('id');
                        let tr = $(this).closest('tr');
                        console.log(id);
                        Swal.fire({
                            title: 'Are you sure?',
                            text: "You won't be able to revert this!",
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: 'Yes, delete it!'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                $.ajax({
                                    type: 'POST',
                                    url: "delete-brand",
                                    data: {brandID: id},
                                    success: function () {
                                        Swal.fire(
                                                'Deleted!',
                                                'Your file has been deleted.',
                                                'success'
                                                );
                                        tr.remove();
                                    }
                                });

                            }
                        });
                    });
                });
            }
        };
brand.init();