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
@str0 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	%1 = call i8* @malloc(i32 20)
	%2 = bitcast i8* %1 to i32*
	store i32 4, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	store i32* %3, i32** @a, align 4
	ret void 
}
define i32 @main()

0:
;prev: 
;next: 17 
	call void @g_init()
	%1 = call i8* @malloc(i32 20)
	%2 = bitcast i8* %1 to i32*
	store i32 4, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	%4 = bitcast i32* %3 to i32**
	%5 = getelementptr inbounds i32*, i32** %4, i32 0
	%6 = load i32*, i32** @a, align 4
	store i32* %6, i32** %5, align 4
	%7 = getelementptr inbounds i32*, i32** %4, i32 1
	%8 = load i32*, i32** @a, align 4
	store i32* %8, i32** %7, align 4
	%9 = getelementptr inbounds i32*, i32** %4, i32 2
	%10 = load i32*, i32** @a, align 4
	store i32* %10, i32** %9, align 4
	%11 = getelementptr inbounds i32*, i32** %4, i32 3
	%12 = load i32*, i32** @a, align 4
	store i32* %12, i32** %11, align 4
	%13 = bitcast i32** %4 to i32*
	%14 = getelementptr inbounds i32, i32* %13, i32 -1
	%15 = load i32, i32* %14, align 4
	%16 = call i8* @_gbl_toString(i32 %15)
	call void @_gbl_println(i8* %16)
	br label %17
17:
;prev: 0 30 
;next: 24 29 
;iDom: 0
;DomFrontiers: 17 
	%18 = phi i32 [ 0, %0 ], [ %31, %30 ]
	%19 = getelementptr inbounds i32*, i32** %4, i32 0
	%20 = load i32*, i32** %19, align 4
	%21 = getelementptr inbounds i32, i32* %20, i32 -1
	%22 = load i32, i32* %21, align 4
	%23 = icmp slt i32 %18, %22
	br i1 %23, label %24, label %29
24:
;prev: 17 
;next: 30 
;iDom: 17
;DomFrontiers: 17 
	%25 = getelementptr inbounds i32*, i32** %4, i32 0
	%26 = load i32*, i32** %25, align 4
	%27 = getelementptr inbounds i32, i32* %26, i32 %18
	%28 = call i32 @_gbl_getInt()
	store i32 %28, i32* %27, align 4
	br label %30
29:
;prev: 17 
;next: 32 
;iDom: 17
	br label %32
30:
;prev: 24 
;next: 17 
;iDom: 24
;DomFrontiers: 17 
	%31 = add i32 %18, 1
	br label %17
32:
;prev: 29 47 
;next: 39 45 
;iDom: 29
;DomFrontiers: 32 
	%33 = phi i32 [ 0, %29 ], [ %48, %47 ]
	%34 = getelementptr inbounds i32*, i32** %4, i32 1
	%35 = load i32*, i32** %34, align 4
	%36 = getelementptr inbounds i32, i32* %35, i32 -1
	%37 = load i32, i32* %36, align 4
	%38 = icmp slt i32 %33, %37
	br i1 %38, label %39, label %45
39:
;prev: 32 
;next: 47 
;iDom: 32
;DomFrontiers: 32 
	%40 = getelementptr inbounds i32*, i32** %4, i32 1
	%41 = load i32*, i32** %40, align 4
	%42 = getelementptr inbounds i32, i32* %41, i32 %33
	%43 = load i32, i32* %42, align 4
	%44 = call i8* @_gbl_toString(i32 %43)
	call void @_gbl_print(i8* %44)
	br label %47
45:
;prev: 32 
;next: 49 
;iDom: 32
	%46 = getelementptr inbounds [0 x i8], [0 x i8]* @str0, i32 0, i32 0
	call void @_gbl_println(i8* %46)
	br label %49
47:
;prev: 39 
;next: 32 
;iDom: 39
;DomFrontiers: 32 
	%48 = add i32 %33, 1
	br label %32
49:
;prev: 45 61 
;next: 56 60 
;iDom: 45
;DomFrontiers: 49 
	%50 = phi i32 [ 0, %45 ], [ %62, %61 ]
	%51 = getelementptr inbounds i32*, i32** %4, i32 2
	%52 = load i32*, i32** %51, align 4
	%53 = getelementptr inbounds i32, i32* %52, i32 -1
	%54 = load i32, i32* %53, align 4
	%55 = icmp slt i32 %50, %54
	br i1 %55, label %56, label %60
56:
;prev: 49 
;next: 61 
;iDom: 49
;DomFrontiers: 49 
	%57 = getelementptr inbounds i32*, i32** %4, i32 2
	%58 = load i32*, i32** %57, align 4
	%59 = getelementptr inbounds i32, i32* %58, i32 %50
	store i32 0, i32* %59, align 4
	br label %61
60:
;prev: 49 
;next: 63 
;iDom: 49
	br label %63
61:
;prev: 56 
;next: 49 
;iDom: 56
;DomFrontiers: 49 
	%62 = add i32 %50, 1
	br label %49
63:
;prev: 60 77 
;next: 70 76 
;iDom: 60
;DomFrontiers: 63 
	%64 = phi i32 [ 0, %60 ], [ %78, %77 ]
	%65 = getelementptr inbounds i32*, i32** %4, i32 3
	%66 = load i32*, i32** %65, align 4
	%67 = getelementptr inbounds i32, i32* %66, i32 -1
	%68 = load i32, i32* %67, align 4
	%69 = icmp slt i32 %64, %68
	br i1 %69, label %70, label %76
70:
;prev: 63 
;next: 77 
;iDom: 63
;DomFrontiers: 63 
	%71 = getelementptr inbounds i32*, i32** %4, i32 3
	%72 = load i32*, i32** %71, align 4
	%73 = getelementptr inbounds i32, i32* %72, i32 %64
	%74 = load i32, i32* %73, align 4
	%75 = call i8* @_gbl_toString(i32 %74)
	call void @_gbl_print(i8* %75)
	br label %77
76:
;prev: 63 
;next: 
;iDom: 63
	ret i32 0
77:
;prev: 70 
;next: 63 
;iDom: 70
;DomFrontiers: 63 
	%78 = add i32 %64, 1
	br label %63
}
