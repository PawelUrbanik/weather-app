formCities.addEventListener('input', () => {
    if (document.querySelector('.citiesCheckbox:checked') !== null){
    submitButton.removeAttribute('disabled');
    }else
    {
    submitButton.setAttribute('disabled', 'disabled');
    }
    })
