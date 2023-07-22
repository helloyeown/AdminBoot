
// 댓글 비동기 통신 처리

// list
const getList = async (replyLast = false, page = 1) => {
	const res = await axios.get(`${realPath}/api/replies/${bno}/list?page=${page}&replyLast=${replyLast}`)
	return res.data
}

// list 함수로 선언
const getListDefault = (replyLast, page) => {
	getList(replyLast, page).then(arr => {
		console.log(arr)
		
		let replyStr = ""
		let replyPagingString = ""

		for(let i=0; i<arr.list.length; i++){
			const {reply, replyer, replyDate, step, gno, rno} = arr.list[i]

			replyStr += `
				<div class="d-flex align-items-center py-3 border-top${step === 0 ? "" : " ps-3"}">
					<div class="w-100">
						<div class="d-flex w-100 justify-content-between">
							<h6 class="mb-0">${reply}</h6>
						</div>
						<span>${replyer}</span>
						<small class="mx-3">${replyDate}</small>
						<button class="btn btn-outline-secondary" data-reply="reply" data-gno="${gno}">Reply</button>
						<button class="btn btn-outline-primary" data-reply="modify" data-rno="${rno}">Modify</button>
					</div>
				</div>
			`
		}

		// const {page, size, startNum, endNum, prevBtn, nextBtn, replyLast, total} = arr
		// console.log(arr)

		replyWrap.innerHTML = replyStr

	})

// regist
// 파라미터로 data를 받아서 post 요청을 보낼 때 JSON 형식으로 HTTP Post 요청의 페이로드로 전달
// 서버로부터의 응답은 res 객체로 받아짐
const postRegist = async (data) => {
	const res = await axios.post(`${realPath}/api/replies/${bno}/regist`, data)
	return res.data
}

}
