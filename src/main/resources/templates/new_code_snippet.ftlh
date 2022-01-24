<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": parseInt(document.getElementById("time_restriction").value),
                "views": parseInt(document.getElementById("views_restriction").value)
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status === 200) {
                alert("Success! Code snippet ID=" + JSON.parse(xhr.responseText).id);
            }
        }
    </script>
</head>
<body>
<form>
    <textarea id="code_snippet"></textarea><br/>
    <label for="time_restriction">Time restriction: </label><input id="time_restriction" type="text"/><br/>
    <label for="views_restriction">Views restriction: </label><input id="views_restriction" type="text"/><br/>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
</html>