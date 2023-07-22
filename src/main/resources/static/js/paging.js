function makePages(page, size, total) {
    let result = "";

    console.log("makePages............")
    // console.log(page, size, total)

    // 페이징 시작 번호
    const startNum = (Math.ceil(page/10) * 10) - 9;

    // 이전 페이지
    // 시작 번호가 1이면 이전 버튼 노출 안 되게
    startNum !== 1 ? result += `<a href="${startNum - 1}" class="btn btn-primary">이전</a>` : ""

    // 페이지 번호 변수
    let temp = startNum;
    // console.log("temp: " + temp)

    // 페이징 버튼 동적 생성
   while(true) {
    // 페이징 번호 * size가 total보다 크면 break
    if(temp * size > total) {
      if(total % (Math.ceil(page / 10) * (10 * size)) !== 1) {
        temp == page ? result += `<a href="${temp}" class="btn btn-primary active">${temp}</a>` : result += `<a href="${temp}" class="btn btn-primary">${temp}</a>`
      }
      break;
    }
    //page와 temp가 같으면 active 처리
    temp === page ? pagingResult += `<a href="${temp}" class="btn btn-primary active">${temp}</a>` : pagingResult += `<a href="${temp}" class="btn btn-primary">${temp}</a>`

    temp++
   }

    //total이 size*10 을 나눈 나머지가 1이면 노출
    total % (Math.ceil(page / 10) * (10 * size)) === 1 ? result += `<a href="${temp}"  class="btn btn-primary">다음</a>` : ""

    // console.log(temp + result)


    return result;

}