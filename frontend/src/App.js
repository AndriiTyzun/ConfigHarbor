import './App.css';

function App() {
  const requestBody = {
    "userEmail" : "user@gmail.com",
    "userPassword" : "admin"
  }

  fetch('api/auth/login', {
    headers : {
      "Content-Type" : "application/json"
    },
    method : "POST",
    body : JSON.stringify(requestBody)
  })
      .then(response => Promise.all([response.json(), response.headers]))
      .then(([body, headers]) => {
        console.log(headers.get("authorization "));
        console.log(body);
      });

  return (
    <div className="App">

    </div>
  );
}

export default App;
