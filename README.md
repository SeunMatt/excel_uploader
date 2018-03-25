excel_uploader
==============
This is a simple JavaScript library that simplify the process of uploading data from large excel files to the server.

Instead of first uploading the entire file to the server and then processing it, this library will save you some bandwidth by 

processing the file on the local machine and uploading the extracted data (which is what you actually want) to the server in batch.

Because it does the upload in batches, issues such as connection timeout is taken care of. In addition to this, it reports the 

data that cause an exception on the server and make them available for download as an Excel file.


Usage
=====
Add the following to your page

```html
<!-- required -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--- optional -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- needed by older browsers -->
<script src="https://github.com/eligrey/Blob.js"></script>

<!-- Required -->
<script src="https://rawgit.com/eligrey/FileSaver.js/src/FileSaver.js"></script>
<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
<script src="https://cdn.rawgit.com/SeunMatt/excel_uploader/4f4ebd93/src/excel_uploader.js"></script>
```

Then initialize the Uploader like this:

```javascript
<script>
    $(document).ready( function () {
        new ExcelUploader({
            maxInAGroup: 1000,
            serverColumnNames: ["Name", "Email", "Phone Number"],
            importTypeSelector: "#dataType",
            fileChooserSelector: "#fileUploader",
            outputSelector: "#tableOutput",
            extraData: {_token: "23333323323223323232"}
        });
    });
</script>
```

Server response
===============
The server is expected to process the data in a transaction and return any set of data that causes an exception as an array of array 
with the data key e.g.
 
 ```javascript
 {
    data : [ ["data1", "data2", "data3"] . . .]
 }
```
It's very important that the response is a JSON type and contains a payload like the one above.

If there were other errors like invalid data or incomplete data. The server can return a JSON with an error entry:

```javascript
{
    error : "This is an error message"
 }
```

Note that the response code should be 200. See the demo code for more info.

And that's all! Happy Bulk Data Processing.

Author
======
Seun Matt - [https://smattme.com](https://smattme.com)


Contributions and Support
=========================
**Love this library? You can support by [buying me a coffee](http://wallet.ng/pay/ossmatt)** :coffee:

Wanna add a feature? or an improvement? Kindly submit a PR.
It's advisable to involve the author from the get go. Cheers
 
Don't forget to star the repo and spread the word.
 