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
	call void @g_init()
	%1 = getelementptr inbounds [3 x i8], [3 x i8]* @str0, i32 0, i32 0
	%2 = getelementptr inbounds [5 x i8], [5 x i8]* @str1, i32 0, i32 0
	%3 = call i8* @_str_concat(i8* %1, i8* %2)
	%4 = call i32 @_str_length(i8* %3)
	%5 = call i32 @_str_ord(i8* %3, i32 5)
	%6 = add i32 %4, %5
	ret i32 %6
}
