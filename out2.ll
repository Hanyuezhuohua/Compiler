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
@INF = common global i32 zeroinitializer, align 4
@n = common global i32 zeroinitializer, align 4
@m = common global i32 zeroinitializer, align 4
@a = common global i32** zeroinitializer, align 4
@str0 = private unnamed_addr constant [2 x i8] c"-1", align 1
@str1 = private unnamed_addr constant [1 x i8] c" ", align 1
@str2 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	store i32 10000000, i32* @INF, align 4
	ret void 
}
define void @init()

0:
;prev: 
;next: 11 
	%1 = call i32 @_gbl_getInt()
	store i32 %1, i32* @n, align 4
	%2 = call i32 @_gbl_getInt()
	store i32 %2, i32* @m, align 4
	%3 = load i32, i32* @n, align 4
	%4 = mul i32 %3, 4
	%5 = add i32 %4, 4
	%6 = call i8* @malloc(i32 %5)
	%7 = bitcast i8* %6 to i32*
	store i32 %3, i32* %7, align 4
	%8 = getelementptr inbounds i32, i32* %7, i32 1
	%9 = bitcast i32* %8 to i32**
	%23 = mv i32 0
	br label %11
11:
;prev: 0 14 
;next: 14 24 
;iDom: 0
;DomFrontiers: 11 
	%12 = add i32 %23, 1
	%13 = icmp ne i32 %23, %3
	br i1 %13, label %14, label %24
14:
;prev: 11 
;next: 11 
;iDom: 11
;DomFrontiers: 11 
	%15 = getelementptr inbounds i32, i32* %7, i32 %23
	%16 = bitcast i32* %15 to i32**
	%17 = load i32, i32* @n, align 4
	%18 = mul i32 %17, 4
	%19 = add i32 %18, 4
	%20 = call i8* @malloc(i32 %19)
	%21 = bitcast i8* %20 to i32*
	store i32 %17, i32* %21, align 4
	%22 = getelementptr inbounds i32, i32* %21, i32 1
	store i32* %22, i32** %16, align 4
	%23 = mv i32 %12
	br label %11
24:
;prev: 11 
;next: 26 
;iDom: 11
	store i32** %9, i32*** @a, align 4
	%48 = mv i32 0
	br label %26
26:
;prev: 24 45 
;next: 29 31 
;iDom: 24
;DomFrontiers: 26 
	%27 = load i32, i32* @n, align 4
	%28 = icmp slt i32 %48, %27
	br i1 %28, label %29, label %31
29:
;prev: 26 
;next: 33 
;iDom: 26
;DomFrontiers: 26 
	%58 = mv i32 0
	br label %33
31:
;prev: 26 
;next: 36 
;iDom: 26
	%61 = mv i32 0
	br label %36
33:
;prev: 29 56 
;next: 39 45 
;iDom: 29
;DomFrontiers: 33 26 
	%34 = load i32, i32* @n, align 4
	%35 = icmp slt i32 %58, %34
	br i1 %35, label %39, label %45
36:
;prev: 31 59 
;next: 49 54 
;iDom: 31
;DomFrontiers: 36 
	%37 = load i32, i32* @n, align 4
	%38 = icmp slt i32 %61, %37
	br i1 %38, label %49, label %54
39:
;prev: 33 
;next: 56 
;iDom: 33
;DomFrontiers: 33 
	%40 = load i32**, i32*** @a, align 4
	%41 = getelementptr inbounds i32*, i32** %40, i32 %48
	%42 = load i32*, i32** %41, align 4
	%43 = getelementptr inbounds i32, i32* %42, i32 %58
	%44 = load i32, i32* @INF, align 4
	store i32 %44, i32* %43, align 4
	br label %56
45:
;prev: 33 
;next: 26 
;iDom: 33
;DomFrontiers: 26 
	%46 = add i32 %48, 1
	%47 = mv i32 %58
	%48 = mv i32 %46
	br label %26
49:
;prev: 36 
;next: 59 
;iDom: 36
;DomFrontiers: 36 
	%50 = load i32**, i32*** @a, align 4
	%51 = getelementptr inbounds i32*, i32** %50, i32 %61
	%52 = load i32*, i32** %51, align 4
	%53 = getelementptr inbounds i32, i32* %52, i32 %61
	store i32 0, i32* %53, align 4
	br label %59
54:
;prev: 36 
;next: 62 
;iDom: 36
	%79 = mv i32 0
	br label %62
56:
;prev: 39 
;next: 33 
;iDom: 39
;DomFrontiers: 33 
	%57 = add i32 %58, 1
	%58 = mv i32 %57
	br label %33
59:
;prev: 49 
;next: 36 
;iDom: 49
;DomFrontiers: 36 
	%60 = add i32 %61, 1
	%61 = mv i32 %60
	br label %36
62:
;prev: 54 74 
;next: 65 73 
;iDom: 54
;DomFrontiers: 62 
	%63 = load i32, i32* @m, align 4
	%64 = icmp slt i32 %79, %63
	br i1 %64, label %65, label %73
65:
;prev: 62 
;next: 74 
;iDom: 62
;DomFrontiers: 62 
	%66 = call i32 @_gbl_getInt()
	%67 = call i32 @_gbl_getInt()
	%68 = call i32 @_gbl_getInt()
	%69 = load i32**, i32*** @a, align 4
	%70 = getelementptr inbounds i32*, i32** %69, i32 %66
	%71 = load i32*, i32** %70, align 4
	%72 = getelementptr inbounds i32, i32* %71, i32 %67
	store i32 %68, i32* %72, align 4
	br label %74
73:
;prev: 62 
;next: 
;iDom: 62
	ret void 
74:
;prev: 65 
;next: 62 
;iDom: 65
;DomFrontiers: 62 
	%75 = add i32 %79, 1
	%76 = mv i32 %66
	%77 = mv i32 %67
	%78 = mv i32 %68
	%79 = mv i32 %75
	br label %62
}
define i32 @main()

0:
;prev: 
;next: 2 
	call void @g_init()
	call void @init()
	%22 = mv i32 0
	br label %2
2:
;prev: 0 19 
;next: 5 8 
;iDom: 0
;DomFrontiers: 2 
	%3 = load i32, i32* @n, align 4
	%4 = icmp slt i32 %22, %3
	br i1 %4, label %5, label %8
5:
;prev: 2 
;next: 11 
;iDom: 2
;DomFrontiers: 2 
	%53 = mv i32 %23
	%54 = mv i32 0
	br label %11
8:
;prev: 2 
;next: 14 
;iDom: 2
	%95 = mv i32 %23
	%96 = mv i32 0
	br label %14
11:
;prev: 5 51 
;next: 17 19 
;iDom: 5
;DomFrontiers: 2 11 
	%12 = load i32, i32* @n, align 4
	%13 = icmp slt i32 %54, %12
	br i1 %13, label %17, label %19
14:
;prev: 8 93 
;next: 24 26 
;iDom: 8
;DomFrontiers: 14 
	%15 = load i32, i32* @n, align 4
	%16 = icmp slt i32 %96, %15
	br i1 %16, label %24, label %26
17:
;prev: 11 
;next: 27 
;iDom: 11
;DomFrontiers: 11 
	%83 = mv i32 0
	br label %27
19:
;prev: 11 
;next: 2 
;iDom: 11
;DomFrontiers: 2 
	%20 = add i32 %22, 1
	%21 = mv i32 %54
	%22 = mv i32 %20
	%23 = mv i32 %53
	br label %2
24:
;prev: 14 
;next: 30 
;iDom: 14
;DomFrontiers: 14 
	%101 = mv i32 0
	br label %30
26:
;prev: 14 
;next: 
;iDom: 14
	ret i32 0
27:
;prev: 17 81 
;next: 33 51 
;iDom: 17
;DomFrontiers: 27 11 
	%28 = load i32, i32* @n, align 4
	%29 = icmp slt i32 %83, %28
	br i1 %29, label %33, label %51
30:
;prev: 24 99 
;next: 55 63 
;iDom: 24
;DomFrontiers: 14 30 
	%31 = load i32, i32* @n, align 4
	%32 = icmp slt i32 %101, %31
	br i1 %32, label %55, label %63
33:
;prev: 27 
;next: 65 81 
;iDom: 27
;DomFrontiers: 27 
	%34 = load i32**, i32*** @a, align 4
	%35 = getelementptr inbounds i32*, i32** %34, i32 %54
	%36 = load i32*, i32** %35, align 4
	%37 = getelementptr inbounds i32, i32* %36, i32 %83
	%38 = load i32**, i32*** @a, align 4
	%39 = getelementptr inbounds i32*, i32** %38, i32 %54
	%40 = load i32*, i32** %39, align 4
	%41 = getelementptr inbounds i32, i32* %40, i32 %22
	%42 = load i32**, i32*** @a, align 4
	%43 = getelementptr inbounds i32*, i32** %42, i32 %22
	%44 = load i32*, i32** %43, align 4
	%45 = getelementptr inbounds i32, i32* %44, i32 %83
	%46 = load i32, i32* %41, align 4
	%47 = load i32, i32* %45, align 4
	%48 = add i32 %46, %47
	%49 = load i32, i32* %37, align 4
	%50 = icmp sgt i32 %49, %48
	br i1 %50, label %65, label %81
51:
;prev: 27 
;next: 11 
;iDom: 27
;DomFrontiers: 11 
	%52 = add i32 %54, 1
	%53 = mv i32 %83
	%54 = mv i32 %52
	br label %11
55:
;prev: 30 
;next: 84 86 
;iDom: 30
;DomFrontiers: 30 
	%56 = load i32**, i32*** @a, align 4
	%57 = getelementptr inbounds i32*, i32** %56, i32 %96
	%58 = load i32*, i32** %57, align 4
	%59 = getelementptr inbounds i32, i32* %58, i32 %101
	%60 = load i32, i32* %59, align 4
	%61 = load i32, i32* @INF, align 4
	%62 = icmp eq i32 %60, %61
	br i1 %62, label %84, label %86
63:
;prev: 30 
;next: 93 
;iDom: 30
;DomFrontiers: 14 
	%64 = getelementptr inbounds [0 x i8], [0 x i8]* @str2, i32 0, i32 0
	call void @_gbl_println(i8* %64)
	br label %93
65:
;prev: 33 
;next: 81 
;iDom: 33
;DomFrontiers: 81 
	%66 = load i32**, i32*** @a, align 4
	%67 = getelementptr inbounds i32*, i32** %66, i32 %54
	%68 = load i32*, i32** %67, align 4
	%69 = getelementptr inbounds i32, i32* %68, i32 %83
	%70 = load i32**, i32*** @a, align 4
	%71 = getelementptr inbounds i32*, i32** %70, i32 %54
	%72 = load i32*, i32** %71, align 4
	%73 = getelementptr inbounds i32, i32* %72, i32 %22
	%74 = load i32**, i32*** @a, align 4
	%75 = getelementptr inbounds i32*, i32** %74, i32 %22
	%76 = load i32*, i32** %75, align 4
	%77 = getelementptr inbounds i32, i32* %76, i32 %83
	%78 = load i32, i32* %73, align 4
	%79 = load i32, i32* %77, align 4
	%80 = add i32 %78, %79
	store i32 %80, i32* %69, align 4
	br label %81
81:
;prev: 33 65 
;next: 27 
;iDom: 33
;DomFrontiers: 27 
	%82 = add i32 %83, 1
	%83 = mv i32 %82
	br label %27
84:
;prev: 55 
;next: 97 
;iDom: 55
;DomFrontiers: 97 
	%85 = getelementptr inbounds [2 x i8], [2 x i8]* @str0, i32 0, i32 0
	call void @_gbl_print(i8* %85)
	br label %97
86:
;prev: 55 
;next: 97 
;iDom: 55
;DomFrontiers: 97 
	%87 = load i32**, i32*** @a, align 4
	%88 = getelementptr inbounds i32*, i32** %87, i32 %96
	%89 = load i32*, i32** %88, align 4
	%90 = getelementptr inbounds i32, i32* %89, i32 %101
	%91 = load i32, i32* %90, align 4
	%92 = call i8* @_gbl_toString(i32 %91)
	call void @_gbl_print(i8* %92)
	br label %97
93:
;prev: 63 
;next: 14 
;iDom: 63
;DomFrontiers: 14 
	%94 = add i32 %96, 1
	%95 = mv i32 %101
	%96 = mv i32 %94
	br label %14
97:
;prev: 84 86 
;next: 99 
;iDom: 55
;DomFrontiers: 30 
	%98 = getelementptr inbounds [1 x i8], [1 x i8]* @str1, i32 0, i32 0
	call void @_gbl_print(i8* %98)
	br label %99
99:
;prev: 97 
;next: 30 
;iDom: 97
;DomFrontiers: 30 
	%100 = add i32 %101, 1
	%101 = mv i32 %100
	br label %30
}