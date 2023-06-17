const sampleForm = document.getElementById("add-user-form");

sampleForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;

    let url = form.action;
    try {
        let formData = new FormData(form);
        let responseData = await postFormFieldsAsJson({ url, formData });
        let { serverDataResponse } = responseData;

        console.log(serverDataResponse);
    } catch (error) {
        console.error(error);
    }
});

async function postFormFieldsAsJson({ url, formData }) {
    let formDataObject = Object.fromEntries(formData.entries());
    let formDataJsonString = JSON.stringify(formDataObject);
    console.log(formDataJsonString);
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: formDataJsonString,
    };

    let res = await fetch(url, fetchOptions);

    if (!res.ok) {
        let error = await res.text();
        throw new Error(error);
    }
    document.getElementById("result").innerHTML = "Udalo sie! Poprawnie dodano uzytkownika do bazy danych!";
    return res.json();
}