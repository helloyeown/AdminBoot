
// 댓글 비동기 통신 처리

// list
const getList = async (replyLast = false, page = 1) => {
	const res = await axios.get(`${realPath}/api/replies/${bno}/list?page=${page}&replyLast=${replyLast}`)
	return res.data
}

// regist
// 파라미터로 data를 받아서 post 요청을 보낼 때 JSON 형식으로 HTTP Post 요청의 페이로드로 전달
// 서버로부터의 응답은 res 객체로 받아짐
const postRegist = async (data) => {
	const res = await axios.post(`${realPath}/api/replies/${bno}/regist`, data)
	return res.data
}

// read
const getRead = async (rno) => {
	const res = await axios.get(`${realPath}/api/replies/read/${rno}`)
	return res.data
}

// delete
const deleteReply = async (rno) => {
	const res = await axios.delete(`${realPath}/api/replies/delete/${rno}`)
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

		// 댓글 페이징
		const {page, size, startNum, endNum, prevBtn, nextBtn, replyLast, total} = arr
		
		prevBtn === true ? replyPagingString += `<li><button data-page="${startNum - 1}" class="btn btn-primary">이전</button></li>` : ""
		
		for(let i = startNum; i <= endNum; i++) {
			replyPagingString += `
			<li${page === i ? " class='active'" : ''}>
			<button data-page="${i}" class="btn btn-primary">${i}</button>
			</li>
			`
		}
		
		nextBtn === true? replyPagingString += `<li><button data-page="${endNum + 1}" class="btn btn-primary">다음</button></li>` : ""
	
		replyWrap.innerHTML = replyStr
		replyPaging.innerHTML = replyPagingString
	})

}
