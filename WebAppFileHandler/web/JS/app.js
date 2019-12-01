
//check upload submission file for empty  and load buffer icon on submit
const form = document.getElementById("file-form");
const p = document.createElement("p");
const container = document.querySelector(".container");
p.textContent = "No file privided! Please choose file first!!";
p.style = "text-align:center;color:brown;";
const loader = document.getElementById("uploading-buffer");

form.addEventListener("submit",
        function (e) {
            const input = document.getElementById("input-file");
            if (input.value === "") {
                container.insertBefore(p, form);
                window.setTimeout(
                        function () {
                            p.remove();
                        }, 2000);
                e.preventDefault();
            } else if (input.value !== "") {
                form.style = "display:none";
                loader.style = "text-align:center; display:block; margin:auto;";
                window.setTimeout(
                        function () {
                            form.submit();
                        }, 2000);
                e.preventDefault();
            }
        }
);



/* 
 * Get file id to use in modal pop up!
 */
const tbody = document.querySelector("tbody");
const deleteForm = document.getElementById("delete-form");

tbody.addEventListener("click",function(e){    
   if(e.target.className==="fa fa-trash"){       
       const row = e.target.parentElement.parentElement.parentElement;
       let id = row.firstElementChild.textContent;
       id=id.trim();
       deleteForm.action = "Remover?file-id="+id;
       const p = deleteForm.querySelector("p");
       p.textContent="Are you sure you want to delete file with #ID "+id+" ?";
   } 
});

