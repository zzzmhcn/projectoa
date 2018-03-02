// JavaScript Document
document.oncontextmenu=new Function("event.returnValue=false;");
document.onselectstart=new Function("event.returnValue=false;");
var _string=new Array();
var _type;
function typetoinput(num)
{
	input=document.getElementById("input-box");
	if(input.name=="type")
	{
		input.value=" ";
		input.name=" ";
	}
	if(num!="."&&input.value[0]==0&&input.value[1]!=".") 
	{
		input.value=num; //Reset input num
	}
	else if(num=="."&&input.value.indexOf(".")>-1)
	{
		input.value=input.value; //Only one point allow input
	}
	else if(input.value=="Infinity"||input.value=="NaN")
	{
		input.value="";
		input.value+=num; //Splicing string
	}
	else
	{
		input.value+=num;
	}
}

function operator(type)
{
	switch (type)
	{
		case "clear":
				input.value="0";
				_string.length=0;
				/*document.getElementById("ccc").innerHTML="";
				for(i=0;i<_string.length;i++)
				{
					document.getElementById("ccc").innerHTML+=_string[i]+" "		
				}*/
				break;
		case "backspace":
				if(checknum(input.value)!=0)
				{
					input.value=input.value.replace(/.$/,'');
					if(input.value=="")
					{
						input.value="0";
					}
				}
				break;
		case "opposite":
				if(checknum(input.value)!=0)
				{
					input.value=-input.value;
				}
				break;
		case "percent":
				if(checknum(input.value)!=0)
				{
					input.value=input.value/100;
				}
				break;
		case "pow":
				if(checknum(input.value)!=0)
				{
					input.value=Math.pow(input.value,2);
				}
				break;
		case "sqrt":
				if(checknum(input.value)!=0)
				{
					input.value=Math.sqrt(input.value);
				}
				break;
		case "plus":
				if(checknum(input.value)!=0)
				{
					_string.push(input.value);
					_type="plus"
					input.value="+";
					input.name="type";
				}
				break;
		case "minus":
				if(checknum(input.value)!=0)
				{
					_string.push(input.value);
					_type="minus"
					input.value="-";
					input.name="type";
				}
				break;
		case "multiply":
				if(checknum(input.value)!=0)
				{
					_string.push(input.value);
					_type="multiply"
					input.value="×";
					input.name="type";
				}
				break;
		case "divide":
				if(checknum(input.value)!=0)
				{
					_string.push(input.value);
					_type="divide"
					input.value="÷";
					input.name="type";
				}
				break;
		case "result":
				if(checknum(input.value)!=0)
				{
					_string.push(input.value);
					if(parseInt(_string.length)%2!=0)
					{
						_string.push(_string[_string.length-2])
					}
					if(_type=="plus")
						{
							input.value=parseFloat(result(_string)[0])+parseFloat(result(_string)[1]);							
							input.name="type"
						}
						else if(_type=="minus")
						{
							input.value=parseFloat(result(_string)[0])-parseFloat(result(_string)[1]);							
							input.name="type"
						}
						else if(_type=="multiply")
						{
							input.value=parseFloat(result(_string)[0])*parseFloat(result(_string)[1]);							
							input.name="type"
						}
						else if(_type=="divide")
						{
							input.value=parseFloat(result(_string)[0])/parseFloat(result(_string)[1]);							
							input.name="type"
						}
					break;
				}
				
	}
}

function result(value)
{
	var result=new Array;
	if(value.length%2==0)
	{
		result.push((value[value.length-2]));
		result.push((value[value.length-1]));
		return (result);
	}
	else
	{
		result.push((value[value.length-1]))
		result.push((value[value.length-2]))
		
		return (result);
	}
}

function checknum(inputvalue)
{
	if(inputvalue=="+"||inputvalue=="-"||inputvalue=="×"||inputvalue=="÷"||input.value=="0")
	{
		return 0;
	}
}


window.document.onkeydown = disableRefresh;
function disableRefresh(evt){
evt = (evt) ? evt : window.event
if (evt.keyCode) 
{
   if(evt.keyCode == 13){operator('result')}
   else if(evt.keyCode == 8){input.focus();window.event.returnValue = false;operator('backspace')}
   else if(evt.keyCode == 27){operator('clear')}
   else if(evt.keyCode == 48){typetoinput('0')}
   else if(evt.keyCode == 49){typetoinput('1')}
   else if(evt.keyCode == 50){typetoinput('2')}
   else if(evt.keyCode == 51){typetoinput('3')}
   else if(evt.keyCode == 52){typetoinput('4')}
   else if(evt.keyCode == 53){typetoinput('5')}
   else if(evt.keyCode == 54){typetoinput('6')}
   else if(evt.keyCode == 55){typetoinput('7')}
   else if(evt.keyCode == 56){typetoinput('8')}
   else if(evt.keyCode == 57){typetoinput('9')}
   else if(evt.keyCode == 96){typetoinput('0')}
   else if(evt.keyCode == 97){typetoinput('1')}
   else if(evt.keyCode == 98){typetoinput('2')}
   else if(evt.keyCode == 99){typetoinput('3')}
   else if(evt.keyCode == 100){typetoinput('4')}
   else if(evt.keyCode == 101){typetoinput('5')}
   else if(evt.keyCode == 102){typetoinput('6')}
   else if(evt.keyCode == 103){typetoinput('7')}
   else if(evt.keyCode == 104){typetoinput('8')}
   else if(evt.keyCode == 105){typetoinput('9')}
   else if(evt.keyCode == 110){typetoinput('.')}
   else if(evt.keyCode == 106){operator('multiply')}
   else if(evt.keyCode == 107){operator('plus')}
   else if(evt.keyCode == 111){operator('divide')}
   else if(evt.keyCode == 109){operator('minus')}
}
}