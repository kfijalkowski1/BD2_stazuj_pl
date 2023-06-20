const sampleForm = document.getElementById("publish-post-form");

sampleForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;

    let url = form.action;
    try {
        let formData = new FormData(form);
        console.log(formData);
        let responseData = await postFormFieldsAsJson({ url, formData });
        let { serverDataResponse } = responseData;

        console.log(serverDataResponse);
    } catch (error) {
        console.error(error);
    }
});

async function postFormFieldsAsJson({ url, formData }) {
    let formDataObject = Object.fromEntries(formData.entries());
    console.log(formDataObject);
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
    document.getElementById("result").innerHTML = "Udalo sie! Poprawnie opublikowano post!";
    return res.json();
}