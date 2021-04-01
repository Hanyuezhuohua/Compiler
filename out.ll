declare i32 @_str_length(i8* %0)
declare i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare i32 @_str_parseInt(i8* %0)
declare i32 @_str_ord(i8* %0, i32 %1)
declare i8* @_str_concat(i8* %0, i8* %1)
declare i1 @_str_eq(i8* %0, i8* %1)
declare i1 @_str_ne(i8* %0, i8* %1)
declare i1 @_str_lt(i8* %0, i8* %1)
declare i1 @_str_gt(i8* %0, i8* %1)
declare i1 @_str_le(i8* %0, i8* %1)
declare i1 @_str_ge(i8* %0, i8* %1)
declare void @_gbl_print(i8* %0)
declare void @_gbl_println(i8* %0)
declare void @_gbl_printInt(i32 %0)
declare void @_gbl_printlnInt(i32 %0)
declare i8* @_gbl_getString()
declare i32 @_gbl_getInt()
declare i8* @_gbl_toString(i32 %0)
declare i8* @malloc(i32 %0)
@str0 = private unnamed_addr constant [3 x i8] c"aaa", align 1
@str1 = private unnamed_addr constant [5 x i8] c"bbbbb", align 1
define void @g_init()

0:
;prev: 
;next: 
	ret void 
}
define i32 @main()

0:
;prev: 
;next: 
	%1 = alloca i8*, align 4
	%2 = alloca i8*, align 4
	%3 = alloca i8*, align 4
	store void , i8** %3, align 0
	store void , i8** %2, align 0
	store void , i8** %1, align 0
	call void @g_init()
	%4 = getelementptr inbounds [3 x i8], [3 x i8]* @str0, i32 0, i32 0
	store i8* %4, i8** %1, align 4
	%5 = getelementptr inbounds [5 x i8], [5 x i8]* @str1, i32 0, i32 0
	store i8* %5, i8** %2, align 4
	%6 = load i8*, i8** %1, align 4
	%7 = load i8*, i8** %2, align 4
	%8 = call i8* @_str_concat(i8* %6, i8* %7)
	store i8* %8, i8** %3, align 4
	%9 = load i8*, i8** %3, align 4
	%10 = call i32 @_str_length(i8* %9)
	%11 = load i8*, i8** %3, align 4
	%12 = call i32 @_str_ord(i8* %11, i32 5)
	%13 = add i32 %10, %12
	ret i32 %13
}
