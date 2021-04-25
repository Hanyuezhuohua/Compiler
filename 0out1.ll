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
@a = common global i32* zeroinitializer, align 4
@m = common global i32 zeroinitializer, align 4
@k = common global i32 zeroinitializer, align 4
@i = common global i32 zeroinitializer, align 4
define void @g_init()

0:
;prev: 
;next: 
;tail: ret void 

	%1 = call i8* @malloc(i32 204)
	%2 = bitcast i8* %1 to i32*
	store i32 50, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	store i32* %3, i32** @a, align 4
	ret void 
}
define i32 @main()

0:
;prev: 
;next: 3 
;tail: br label %3

	call void @g_init()
	%1 = call i32 @_gbl_getInt()
	store i32 %1, i32* @m, align 4
	%2 = call i32 @_gbl_getInt()
	store i32 %2, i32* @k, align 4
	store i32 0, i32* @i, align 4
	br label %3
3:
;prev: 0 13 
;next: 7 12 
;iDom: 0
;DomFrontiers: 3 
;tail: br i1 %6, label %7, label %12

	%4 = load i32, i32* @i, align 4
	%5 = load i32, i32* @m, align 4
	%6 = icmp slt i32 %4, %5
	br i1 %6, label %7, label %12
7:
;prev: 3 
;next: 13 
;iDom: 3
;DomFrontiers: 3 
;tail: br label %13

	%8 = load i32, i32* @i, align 4
	%9 = load i32*, i32** @a, align 4
	%10 = getelementptr inbounds i32, i32* %9, i32 %8
	%11 = call i32 @_gbl_getInt()
	store i32 %11, i32* %10, align 4
	br label %13
12:
;prev: 3 
;next: 16 
;iDom: 3
;tail: br label %16

	store i32 0, i32* @i, align 4
	br label %16
13:
;prev: 7 
;next: 3 
;iDom: 7
;DomFrontiers: 3 
;tail: br label %3

	%14 = load i32, i32* @i, align 4
	%15 = add i32 %14, 1
	store i32 %15, i32* @i, align 4
	br label %3
16:
;prev: 12 44 
;next: 27 33 
;iDom: 12
;DomFrontiers: 16 
;tail: br i1 %26, label %27, label %33

	%17 = load i32, i32* @i, align 4
	%18 = load i32*, i32** @a, align 4
	%19 = getelementptr inbounds i32, i32* %18, i32 %17
	%20 = load i32, i32* @k, align 4
	%21 = sub i32 %20, 1
	%22 = load i32*, i32** @a, align 4
	%23 = getelementptr inbounds i32, i32* %22, i32 %21
	%24 = load i32, i32* %19, align 4
	%25 = load i32, i32* %23, align 4
	%26 = icmp sge i32 %24, %25
	br i1 %26, label %27, label %33
27:
;prev: 16 
;next: 33 
;iDom: 16
;DomFrontiers: 33 
;tail: br label %33

	%28 = load i32, i32* @i, align 4
	%29 = load i32*, i32** @a, align 4
	%30 = getelementptr inbounds i32, i32* %29, i32 %28
	%31 = load i32, i32* %30, align 4
	%32 = icmp sgt i32 %31, 0
	br label %33
33:
;prev: 16 27 
;next: 35 39 
;iDom: 16
;DomFrontiers: 16 
;tail: br i1 %34, label %35, label %39

	%34 = phi i1 [ 0, %16 ], [ %32, %27 ]
	br i1 %34, label %35, label %39
35:
;prev: 33 
;next: 39 
;iDom: 33
;DomFrontiers: 39 
;tail: br label %39

	%36 = load i32, i32* @i, align 4
	%37 = load i32, i32* @m, align 4
	%38 = icmp slt i32 %36, %37
	br label %39
39:
;prev: 33 35 
;next: 41 42 
;iDom: 33
;DomFrontiers: 16 
;tail: br i1 %40, label %41, label %42

	%40 = phi i1 [ 0, %33 ], [ %38, %35 ]
	br i1 %40, label %41, label %42
41:
;prev: 39 
;next: 44 
;iDom: 39
;DomFrontiers: 16 
;tail: br label %44

	br label %44
42:
;prev: 39 
;next: 
;iDom: 39
;tail: ret i32 0

	%43 = load i32, i32* @i, align 4
	call void @_gbl_printInt(i32 %43)
	ret i32 0
44:
;prev: 41 
;next: 16 
;iDom: 41
;DomFrontiers: 16 
;tail: br label %16

	%45 = load i32, i32* @i, align 4
	%46 = add i32 %45, 1
	store i32 %46, i32* @i, align 4
	br label %16
}
