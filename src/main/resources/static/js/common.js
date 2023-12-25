window.onload = () => {
    const userHeader = document.getElementById("userHeaderIcon");
    console.log(userHeader);
    if (localStorage.getItem("isLoggedIn") === "true") {
        userHeader.innerHTML = "<i class=\"fa-solid fa-user\" onclick='hadnleGoProfile()'></i>";
    } else {
        userHeader.innerHTML = "<p class='text-[18px] font-[500] text-black' onclick='handleGoLogin()'>Log in</p>";
    }
};

const handleGoLogin = () => {
    window.location.href = "http://localhost:8080/login";
};
const hadnleGoProfile=()=>{
    window.location.href = "http://localhost:8080/profile";
}

const deleteCartItem=async (id)=>{

    const res=await fetch("http://localhost:8080/cartItem?id="+id,{
        method:"DELETE",
    })
    if(res.status===200){
        window.location.reload();
    }
}

const handleImgClick=()=>{
    window.location.href="http://localhost:8080/"
}

const handleCartClick=()=>{
    window.location.href="http://localhost:8080/cart?id="+localStorage.getItem("userId")
}

const order=async (id)=>{
    const res=await fetch("http://localhost:8080/cartItem?id="+id,{
        method:"DELETE",
    })
    if(res.status===200){
        window.location.reload();
    }
}