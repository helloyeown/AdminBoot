function makePages(page, size, total) {
    let result = "";

    console.log("makePages............")
    // console.log(page, size, total)

    // 페이징 시작 번호
    const startNum = (Math.ceil(page/10) * 10) - 9;

    //이전 페이지
    if(startNum !== 1){
        result += `<li class="page-item"><a class="page-link"
        href="list?page=${startNum-1}">이전</a></li>`
        }

    // 페이지 번호 변수
    let temp = startNum;
    // console.log("temp: " + temp)

    // 페이징 버튼 동적 생성
    while(true){

        if(temp * size > total){
          break;
        }

        result +=`<li class="page-item"><a class="page-link"
        href="list?page=${temp}">${temp}</a></li>`
        // console.log("result", result)
        temp++;

      }

    if(total % (size * 10) === 1){
        result += `<li class="page-item"><a class="page-link"
        href="list?page=${startNum + 10}">다음</a></li>`
      }

    // console.log(temp + result)


    return result;

}