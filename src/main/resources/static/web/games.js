$(document).ready(() => {
    $.ajax({ url:'/api/games', success: getAndSetData });
});

function getAndSetData(data) {
    console.log(data);
    //agrega data xdxd
}
