// 파일 보여주기
const showImages = (arr) => {
    let str = ""
    for (const uploadFile of arr) {
        // console.log(uploadFile)     // fileName, img, uuid, link

        const {fileName, uuid, link} = uploadFile




        str += `
            <li data-originName="${uuid}_${fileName}" style="list-style: none">
                <a href="http://localhost/${uuid}_${fileName}" target="_blank">
                    <img src="http://localhost/${link}">
                </a>
                <p>${fileName}</p>
                <button class="btn btn-danger" onclick="javascript:removeFile(event, '${uuid}', '${fileName}')">X</button>
            </li>
        `
    }
    uploadUL.innerHTML += str
}

// 파일 삭제
const removeFile = (e, uuid, fileName) => {
    e.preventDefault()
    e.stopPropagation()

    const liObj = e.target.closest("li")
    // console.log(liObj)

    let originFile = ""
    fileName !== undefined ? originFile = uuid + "_" + fileName : originFile = uuid

    console.log(originFile)

    // nginx에서 파일 삭제
    axios.delete(`http://localhost:8080/api/files/remove/${originFile}`)

    // li 삭제
    liObj.remove()

}