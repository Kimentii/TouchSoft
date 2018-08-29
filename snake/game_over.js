window.onload = function () {
    const score = localStorage.getItem("score");
    const scoreHeader = document.getElementById("header_score");
    scoreHeader.textContent += score;
};