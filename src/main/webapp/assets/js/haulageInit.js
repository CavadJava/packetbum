    $(function(){
            	  
            	  $('select.styled').customSelect();
            	  var resean="";
        		  var counter = $("#numForms").val();
        		  var imageItemCount=$("#numForms").val();
        		  
        		  var editItem;
        		  
        		  var subImageDiv="<div id='itemImage_"+counter+"'></div>";
        		  $("#displayImage").append(subImageDiv);
        		  
        		  var counterImage =counter;
        		  
        		  
        		
        		  
        		  
        		  $("#btn_browse").click(function(){
        			
                        
        			  
        			  var inputFile="<input type='file' id='itemFileImage_"+counterImage+"' style='display:none;' multiple/>";
        			   
        			var change=0;
        			  
        			  console.log("value:"+inputFile);
        			  
        			
        			    $("#imageInput").append(inputFile);
        			   
        			    $("#itemFileImage_"+counterImage).click();
        			    
        			    $("#itemFileImage_"+counterImage).change(function(){
                   		 
        			    	change++;
                  		  var imageURL=this.files;
                  		  
                  		  console.log("imageURL"+imageURL);
                  		  $('#imageUploadSuccessID').hide();
                  		  $('#ajaxLoaderID').show();
                  		  
                  		 // var filesSelected = document.getElementById("btn_photo").files;
                  		  
                  		  console.log("image:"+imageURL.length);
                  		  var imageLengthCount=$("#itemImage_"+counter+" > input").length;
                  		  
                  		// console.log("imageLengthCount:"+imageLengthCount);
                  		  var imageLength=$("#itemImage_"+counter+" > input").length;
                  		 console.log("imageLength:"+imageLength);
                  		     var j=1;
                  		  //
                  		  
                  		   var imageLengthCount1=$("#itemImage_"+counter+" > input").length;
                  		  
                  		  if(imageURL && imageURL[0]) for(var i=0; i<imageURL.length; i++) {
                  			  
                  			  
                  			var filename = imageURL[i].name;
            			 	
           			 	// console.log("files name1"+filename);
                          var lastIndex = filename.lastIndexOf("\\");
                         if (lastIndex >= 0) {
                         filename = filename.substring(lastIndex + 1);
                          }
                        
                 		  
                  		 console.log("imageLengthCount:"+imageLengthCount1);
                       //  console.log("files name2"+filename);
           		           AppUtil.readImage(imageURL[i],imageLengthCount1,"itemImage_"+imageItemCount,filename);
           		  
                  			 // console.log("files length"+imageURL[i]);		  
                  		   //  AppUtil.readImage(imageURL[i],j,"itemImage_"+imageItemCount);
                  		  if(imageURL.length>1){
                  			imageLengthCount1++;
                  		  }
                  		    j++;
                  		  }
                  		  
                  		  
                  		 var inputNumImage="<input type='hidden' id='itemNumImage_"+counterImage+"' name='itemNumImage_"+counterImage+"' value='"+imageURL.length+"'/>";
          			     $("#imageInput").append(inputNumImage);
                  		  
                  	
                  		  $('#ajaxLoaderID').hide();
                  		    
                  		  $('#imageUploadSuccessID').show();
                  			
                  	
                  		       counterImage++;  
                  		       
                  		                 		       
                  		       
                  		       
                  		    }); 
                  		
        			    
        			
                    	
                    	
                  		  if(change==0){
                  			$("itemFileImage_"+counterImage).remove();
                  		  }
                  		  
                  		  
                  	  });
        			  
        		
        		  
        		
        		  
        		  $("#btn_cancelID").click(function(){
        			  $(".dimension").hide();
        			  $("#categorySelectBoxID").find('span.holder').html("Select");
        			  $('#categoryID option:contains("0")').prop('selected', true);
        			  $(".holder").val('select')
        			  $("#descriptionID").val('');
                		$("#packagedescriptionID").val('');
                		$("#relevantdimensionID").val(''); 
                		 
                		$("#weightID").val(''); 
        			  
        		  });
            	  $("#addAnotherID").click(function(){
            		 
            		  
            		  $(".dimension").show();
            			$("#funnitureForm").show();
            			
            			 $("#descriptionID").val('');
                 		$("#packagedescriptionID").val('');
                 		$("#relevantdimensionID").val(''); 
                 		 
                 		$("#weightID").val(''); 
            		  
            		  
            		  
            	  });
            	  
            	  
            	  
            	  
            	  
            	  $("#showimageID").click(function(){
            		  
            		//  alert("dsfsdf");
            		
            	//var imageLength=$("#itemImage_"+counter+" > img").length;
            	
            	console.log("counter"+counter);
            	
            	
            	var imageLength=$("#itemImage_"+counter+" > input").length;

            	//var imageSrc=$('#itemImage_1').children('#image_0').val();
            
            		
            	console.log("length"+imageLength);
            	
            	 var html ="<ul id='imageulID'>";
            	
            	for(i=0;i<imageLength;i++){
            		
            		var imageSrc=$("#itemImage_"+counter).children('#image_'+i).val();
            		
            		html+="<li><img src='"+imageSrc+"' style='width:110px;height:100px'/></li>";
            		
            	}
            	
            	html+="</ul>";
            		 
            	  
            		  config = {
            					"body": html,
            					"title": '<strong>Item Images</strong>',
            					"top":'50%'
            		           };
            				popUp = new PopUp(config);
            				popUp.render();
            	  
            	              	  
            	  });
            	 
            	
            	  
            	  $("#btn_photo").change(function(){
            		 
            		  var imageURL=this.files;
            		  $('#imageUploadSuccessID').hide();
            		  $('#ajaxLoaderID').show();
            		  
            		 // var filesSelected = document.getElementById("btn_photo").files;
            		  
            		  console.log("imageURL"+imageURL);
            		  var imageLengthCount=$("#itemImage_"+counter+" > input").length;
            		  
            		  console.log("imageLengthCount:"+imageLengthCount);
            		  var imageLength=$("#itemImage_"+imageItemCount+" > input").length;
            		  console.log("imageLengthCount:"+imageLengthCount);
            		     var j=1;
            		  //
            		  
            		  if(imageURL && imageURL[0]) for(var i=0; i<imageURL.length; i++) {
            			  
            			 // console.log("files length"+imageURL[i]);		 
            			 
            			 	var filename = imageURL[i].name;
            			 	
            			 	 console.log("files name1"+filename);
                           var lastIndex = filename.lastIndexOf("\\");
                          if (lastIndex >= 0) {
                          filename = filename.substring(lastIndex + 1);
                           }
                          console.log("files name2"+filename);
                          
                      
            		     AppUtil.readImage(imageURL[i],j,"itemImage_"+imageItemCount,filename);
            		  
            		    j++;
            		  }
            		  
            		  var filesSelected = document.getElementById("btn_photo").files;
            		  subImageDiv+="<input type='file' id='itemImageFile_"+imageItemCount+"' value='"+filesSelected+"'  /> ";  
            		              		  
            		   $("#itemImage_"+imageItemCount).append(subImageDiv);
            		  $('#ajaxLoaderID').hide();
            		    
            		  $('#imageUploadSuccessID').show();
            			
            		  
            		 /*  AppUtil.readImage(imageURL, function(base64Img){
            		        //$('.output').find('img').attr('src', base64Img);            		        
            		        var subImageDiv="<div id='itemImage_'"+counter+">";
                  		  
                  		  subImageDiv+="<input type='hidden' id='image_'"+counter+"value='"+base64Img+"' /> </div>";              		  
                  		  
                  		  $("#displayImage").append(subImageDiv);
            		        
            		    });
            		   */
            		  
            		  
            		  
            	  });
            	  
            	  
            	  $("#doneID").click(function(){
            		  
            		  
            		  
            		 // alert("formcount"+counter);
            		 
            		  
            		
            		 
            		var description=$("#descriptionID").val();
          		    
                  	var packagedescription=$("#packagedescriptionID").val();                            	
                  	
                  	var relevantdimension=$("#relevantdimensionID").val();    	
                  
                  
                  	var weight=$("#weightID").val();     
                  	
                  	var weight_type=$("#weight_typeID").val();
                  	
                 
                  	if(description!=''&&packagedescription!=''&&relevantdimension!=''&&weight!=''){
                  		
                  		
                  		resean='';
                  		
                  		
                  	}else{
                  		resean="Enter All Require Fields";
                  	}
                            
                  	
                  	
                  	$("#errorMessageSection").html(resean);       
                  	
                  	console.log(resean);
                  	
                  	if(resean==''){
                  		
                  		$("#noItemAddedID").hide();
                  		
                  		var addItem="<div class='item_1' id='itemID_"+counter+"'>";
                  		
                  	//	var dymensionCal="<li>"+length+dimension+" X "+height+dimension+" X "+width+dimension+"</li><li>"+weight+weight_type+", Qty:"+Quantity+"</li>";
                  		
                  		addItem+="<h4>Item "+counter+"</h4>";
                  		addItem+="<div class='item_fur'>";
                  		addItem+="<ul><li>"+description+"</li><li>"+relevantdimension+"</li><li>"+weight+weight_type+"</li></ul>";
                  		addItem+="<ul class='edit_del'><li><a  id='editItem_"+counter+"' data='"+counter+"' class='btn_edit'></a></li><li><a  class='btn_del'  data='"+counter+"' id='removeItem_"+counter+"'></a></li></ul>";                   		
                  		addItem+="</div></div>";               		
                  		
                  		
                  		
                  		$("#itemDetailID").append(addItem);
                  		
                  		 $("#addAnotherID").show();
                  		
                  		var formVal="";
                  
                  		
                  
                  		    formVal="<input  type='hidden'  id='description_"+counter+"' name='description_"+counter+"' value='"+description+"'/>";
                  			formVal+="<input type='hidden' id='packagedescription_"+counter+"' name='packagedescription_"+counter+"' value='"+packagedescription+"'/>";
                  			formVal+="<input type='hidden' id='relevantdimension_"+counter+"' name='relevantdimension_"+counter+"' value='"+relevantdimension+"'/>";            		
                  	
                  			formVal+="<input type='hidden' id='weight_"+counter+"' name='weight_"+counter+"' value='"+weight+"'/>";
                  			formVal+="<input type='hidden' id='weight_type_"+counter+"' name='weight_type_"+counter+"' value='"+weight_type+"'/>";
                  		
                  		
                  		
                  		$("#itemID_"+counter).append(formVal);
                  		
                  		
                  		$("#removeItem_"+counter).on("click",function(event){
                  			
                  			 var deleteitem=$(this).attr('data');
                  			console.log("hi"+deleteitem);
                  			$("#itemID_" + deleteitem).remove();
                  			$("#addAnotherID").hide();
                  			
                  		});
                  		
                  		
                  		$("#editItem_"+counter).on("click",function(event){
                  			
                 			 //$("#itemID_" + counter).remove();                 			 
                 			             			 
                  			$("#funnitureForm").show();
                  			$(".dimension").show();
                  			editItem=$(this).attr('data');
                  			
                  		
                  		
                  			console.log("edit Item:"+editItem);
                  			
                  			//var editDescription=;                  			
                  			$("#descriptionID").val($("#description_"+editItem).val());
                  			$("#packagedescriptionID").val($("#packagedescription_"+editItem).val());
                  			$("#relevantdimensionID").val($("#relevantdimension_"+editItem).val());                  			
                  			$("#weightID").val($("#weight_"+editItem).val());
                  			$("#weight_typeID").val($("#weight_type_"+editItem).val());                 			
                  			
                  			
                  			$("#btn_cancelID").hide();
                  			$("#doneID").hide();
                  			$("#btn-updateID").show();
                  			
                  				$("#addAnotherID").hide();
                          
                  			 $('#itemID_'+editItem).remove();  
                  			 
                  		
                  			
                 		});
                  		
                  		
                  		$("#funnitureForm").hide();
                  		
                  		counter++;
                  		
                  		$("#numForms").val(counter);
                  		
                  		 $("#descriptionID").val('');
                  		$("#packagedescriptionID").val('');
                  		$("#relevantdimensionID").val(''); 
                  		 
                  		$("#weightID").val(''); 
                  		imageItemCount++;
            		  
            		  var subImageDiv="<div id='itemImage_"+imageItemCount+"'></div>";
            		  $("#displayImage").append(subImageDiv);
            		  
            		  $(".dimension").hide();
                  		
                  	}
                  	
                  	
            	  
            	  
            	  });
            	  
            	  
            	  $("#btn-updateID").click(function(){
            			
            			$(".dimension").hide();  
            			var description=$("#descriptionID").val();
              		    
                      	var packagedescription=$("#packagedescriptionID").val();                            	
                      	
                      	var relevantdimension=$("#relevantdimensionID").val();    	
                      
                      
                      	var weight=$("#weightID").val();     
                      	
                      	var weight_type=$("#weight_typeID").val();
                    	
            			console.log("edit Item 2:"+editItem);
            			
            			
                         var addItem="<div class='item_1' id='itemID_"+editItem+"'>";
                  		
                  	//	var dymensionCal="<li>"+length+dimension+" X "+height+dimension+" X "+width+dimension+"</li><li>"+weight+weight_type+", Qty:"+Quantity+"</li>";
                  		
                  		addItem+="<h4>Items"+editItem+"</h4>";
                  		addItem+="<div class='item_fur'>";
                  		addItem+="<ul><li>"+description+"</li><li>"+relevantdimension+"</li><li>"+weight+weight_type+"</li></ul>";
                  		addItem+="<ul class='edit_del'><li><a  id='editItem_"+editItem+"' data='"+editItem+"' class='btn_edit'></a></li><li><a href='#' class='btn_del' id='removeItem_"+editItem+"'></a></li></ul>";                   		
                  		addItem+="</div></div>";   
            		  
            		  /*  $("#description_"+editItem).val($("#descriptionID").val());
                		$("#length_"+editItem).val($("#lengthID").val());
                		$("#height_"+editItem).val($("#heightID").val()); 
                		$("#width_"+editItem).val($("#widthID").val()); 
                		$("#weight_"+editItem).val($("#weightID").val()); 
                		$("#dimension_"+editItem).val($("#dimensionID").val()); 
                		$("#weight_type_"+editItem).val($("#weight_typeID").val()); 
                		$("#quantity_"+editItem).val($("#quantityID").val()); */
                		
                		
                		$("#itemDetailID").append(addItem);
                  		
                 		 $("#addAnotherID").show();
                 		
                 		var formVal="";
                 		
                 		
                 		
                 		
                 		
                 		    formVal="<input  type='hidden'  id='description_"+editItem+"' name='description_"+editItem+"' value='"+description+"'/>";
                 			formVal+="<input type='hidden' id='packagedescription_"+editItem+"' name='packagedescription_"+editItem+"' value='"+packagedescription+"'/>";
                 			formVal+="<input type='hidden' id='relevantdimension_"+editItem+"' name='relevantdimension_"+editItem+"' value='"+relevantdimension+"'/>";
                 			formVal+="<input type='hidden' id='weight_"+editItem+"' name='weight_"+editItem+"' value='"+weight+"'/>";
                 			formVal+="<input type='hidden' id='weight_type_"+editItem+"' name='weight_type_"+editItem+"' value='"+weight_type+"'/>";
                 			
                 		
                 		
                 		$("#itemID_"+editItem).append(formVal);
                 		
                 		$("#removeItem_"+editItem).on("click",function(event){
                  			
                 			 var deleteitem=$(this).attr('data');
                 			console.log("hi"+deleteitem);
                 			$("#itemID_" + deleteitem).remove();
                 			$("#addAnotherID").hide();
                 			
                 		});
                 		
                 		
                 		$("#editItem_"+editItem).on("click",function(event){
                 			
                			 //$("#itemID_" + counter).remove();                 			 
                			  	$(".dimension").show();           			 
                 			$("#funnitureForm").show();
                 			editItem1=$(this).attr('data');
                 			
                 		
                 			 
                 			console.log("edit Item:"+editItem1);
                 			
                 			  ////packagedescriptionID relevantdimensionID weightID weight_typeID
                 			
                 			//var editDescription=;                  			
                 			$("#descriptionID").val($("#description_"+editItem1).val());
                 			$("#packagedescriptionID").val($("#packagedescription_"+editItem1).val());
                 			$("#relevantdimensionID").val($("#relevantdimension_"+editItem1).val());
                 			
                 			$("#weightID").val($("#weight_"+editItem1).val());
                 			$("#weight_typeID").val($("#weight_type_"+editItem1).val());
                 		
                 			
                 			
                 			$("#btn_cancelID").hide();
                 			$("#doneID").hide();
                 			$("#btn-updateID").show();
                 			
                 				$("#addAnotherID").hide();
                         
                 			 $('#itemID_'+editItem).remove();  
                 			
                		});
                		
            			$("#btn_cancelID").show();
            			$("#doneID").show();
            			
            			$("#btn-updateID").hide();
            			
            			$("#funnitureForm").hide();
            			$("#addAnotherID").show();
            		
            		});
            	  
            	
            		  
            	  
            	  
              });
              
              
              
              
          