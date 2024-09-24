document.getElementById('submit-btn').addEventListener('click', function() {
    const question = document.getElementById('question').value;

    if (!question) {
        alert("질문이 작성되지 않았습니다");
        return;
    }

    fetch('/api/ask-ai', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ question: question })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('answer').textContent = data.answer;
        })
        .catch(error => {
            document.getElementById('answer').textContent = "에러 : AI 연결 실패";
            console.error('Error:', error);
        });
});
